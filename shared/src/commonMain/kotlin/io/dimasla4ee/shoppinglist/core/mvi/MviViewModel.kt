package io.dimasla4ee.shoppinglist.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Базовая реализация ViewModel для построения архитектуры MVI (Model-View-Intent).
 *
 * Ключевые компоненты:
 * - **State** ([state]): Горячий поток данных ([StateFlow]), представляющий актуальное состояние UI.
 * - **Effect** ([effects]): Канал одноразовых событий (навигация, показ Toast/Snackbar).
 *   Используется [Channel], чтобы гарантировать доставку события ровно один раз (защита от повторного
 *   срабатывания при пересоздании View, например, при повороте экрана).
 * - **Intent** ([dispatch]): Намерение пользователя или системное событие, инициирующее изменение.
 *
 * ### Пример использования:
 * ```kotlin
 * // 1. Контракты
 * data class MyState(
 *     val isLoading: Boolean = false,
 *     val data: String = ""
 * ) : MviState
 *
 * sealed interface MyIntent : MviIntent {
 *     object LoadData : MyIntent
 *     data class DataLoaded(val data: String) : MyIntent
 * }
 *
 * sealed interface MyEffect : MviEffect {
 *     data class ShowError(val message: String) : MyEffect
 * }
 *
 * // 2. ViewModel
 * class MyViewModel : MviViewModel<MyIntent, MyState, MyEffect>(MyState()) {
 *     override fun reduce(intent: MyIntent, current: MyState): MyState = when (intent) {
 *         is MyIntent.LoadData -> current.copy(isLoading = true)
 *         is MyIntent.DataLoaded -> current.copy(isLoading = false, data = intent.data)
 *     }
 *
 *     override suspend fun handleIntent(intent: MyIntent) {
 *         if (intent is MyIntent.LoadData) {
 *             try {
 *                 val result = repository.fetchData() // suspending call
 *                 dispatch(MyIntent.DataLoaded(result))
 *             } catch (e: Exception) {
 *                 emitEffect(MyEffect.ShowError("Ошибка загрузки"))
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * @param I Тип намерений (Intent).
 * @param S Тип состояния (State).
 * @param E Тип одноразовых событий (Effect).
 * @param initialState Начальное состояние экрана по умолчанию.
 */
abstract class MviViewModel<I : MviIntent, S : MviState, E : MviEffect>(
    initialState: S,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effects = Channel<E>(Channel.BUFFERED)
    val effects: Flow<E> = _effects.receiveAsFlow()

    /**
     * Точка входа для всех действий и намерений из View.
     *
     * Жизненный цикл обработки намерения:
     * 1. Синхронно вычисляет и применяет новое состояние через функцию [reduce].
     * 2. Асинхронно запускает обработку побочных эффектов через [handleIntent]
     *    в рамках [viewModelScope].
     *
     * @param intent Намерение, которое необходимо обработать.
     */
    fun dispatch(intent: I) {
        _state.value = reduce(intent, _state.value)
        viewModelScope.launch { handleIntent(intent) }
    }

    /**
     * Чистая функция для вычисления нового состояния.
     *
     * Вызывается синхронно при каждом вызове [dispatch].
     * **Важно:** В этом методе не должно быть никаких побочных эффектов (сетевых запросов,
     * работы с БД и т.д.). Допускается только синхронное преобразование текущего состояния
     * в новое на основе полученного намерения.
     *
     * @param intent Пришедшее намерение.
     * @param current Текущее состояние.
     * @return Новое, обновленное состояние.
     */
    protected abstract fun reduce(intent: I, current: S): S

    /**
     * Метод для обработки побочных эффектов (Side-effects).
     *
     * Вызывается асинхронно после отработки [reduce]. Используется для:
     * - Выполнения сетевых запросов.
     * - Чтения/записи в базу данных.
     * - Отправки событий во View через [emitEffect].
     * - Запуска новых намерений через [dispatch] по результатам работы.
     *
     * @param intent Намерение, для которого требуются асинхронные действия.
     */
    protected open suspend fun handleIntent(intent: I) = Unit

    /**
     * Вспомогательный метод для прямого обновления состояния.
     *
     * Полезен внутри [handleIntent] для изменения стейта по результатам
     * асинхронных операций (например, загрузки данных), когда создание
     * отдельного Intent для этого изменения кажется избыточным.
     *
     * @param transform Функция-преобразователь текущего состояния в новое.
     */
    protected fun updateState(transform: (S) -> S) {
        _state.value = transform(_state.value)
    }

    /**
     * Отправляет одноразовое событие (Effect) во View.
     *
     * Эффекты буферизируются в канале, что гарантирует их доставку подписчику,
     * даже если View в данный момент не активно (например, пересоздается).
     * При этом эффект будет обработан строго один раз.
     *
     * @param effect Событие для отправки (например, команда навигации или показ Snackbar).
     */
    protected suspend fun emitEffect(effect: E) {
        _effects.send(effect)
    }
}
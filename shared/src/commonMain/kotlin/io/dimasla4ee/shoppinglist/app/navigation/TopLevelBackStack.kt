package io.dimasla4ee.shoppinglist.app.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

/**
 * Класс для управления стеком навигации на верхнем уровне приложения.
 * Хранит и управляет историей переходов между экранами (ключами навигации).
 * Позволяет добавлять, удалять и заменять элементы стека.
 *
 * @param T тип ключа навигации ([NavKey]), который используется в стеке.
 * @param startKey начальный ключ навигации, с которого начинается стек.
 * @since 1.0.0
 */
class TopLevelBackStack<T : NavKey>(private val startKey: T) {

    /**
     * Карта стеков навигации для разных топовых ключей.
     * Ключ карты — текущий топовый ключ, значение — список ключей навигации (стек).
     * Инициализируется с начальным стеком, содержащим только `startKey`.
     */
    private var topLevelBackStacks = mutableStateMapOf(
        startKey to mutableStateListOf(startKey)
    )

    /**
     * Текущий топовый ключ навигации.
     * Изменяется при переходе на новый топовый экран.
     * Нельзя изменять извне — только через внутренние методы класса.
     */
    var topLevelKey by mutableStateOf(startKey)
        private set

    /**
     * Полный стек навигации, объединяющий начальный стек и текущий стек.
     * Если `topLevelKey` совпадает с `startKey`, возвращается только текущий стек.
     * Если `topLevelKey` отличается от `startKey`, объединяется начальный стек
     * (`startKey` и последующие ключи) с текущим стеком.
     *
     * @return список ключей навигации в порядке от нижнего к верхнему уровню стека.
     */
    val backStack: List<T>
        get() {
            return when {
                topLevelKey == startKey -> currentStack
                else -> {
                    val startStack = topLevelBackStacks[startKey] ?: emptyList()
                    startStack + currentStack
                }
            }
        }

    /**
     * Текущий стек навигации для `topLevelKey`.
     * Возвращает список ключей навигации для текущего топового экрана.
     * Если стек не найден для `topLevelKey`, возвращается пустой [SnapshotStateList].
     *
     * @return текущий стек навигации как [SnapshotStateList].
     */
    private val currentStack: SnapshotStateList<T>
        get() = topLevelBackStacks[topLevelKey] ?: SnapshotStateList()

    /**
     * Добавляет ключ навигации в текущий стек.
     *
     * @param key ключ навигации ([T]), который нужно добавить в стек.
     */
    fun add(key: T) {
        currentStack.add(key)
    }

    /**
     * Удаляет последний ключ из текущего стека.
     * Если в стеке остаётся только начальный ключ (`startKey`),
     * сбрасывает `topLevelKey` к `startKey`.
     *
     * Логика:
     * - Если в стеке больше одного элемента, удаляет последний.
     * - Если стек состоит только из `topLevelKey` (не равного `startKey`),
     *   возвращает `topLevelKey` к `startKey`.
     */
    fun removeLast() {
        when {
            currentStack.size > 1 -> currentStack.removeLastOrNull()
            topLevelKey != startKey -> topLevelKey = startKey
        }
    }

    /**
     * Заменяет текущий стек навигации набором заданных ключей.
     *
     * @param keys массив ключей навигации, которые заменят текущий стек.
     * @throws IllegalArgumentException если массив `keys` пуст.
     */
    fun replaceStack(vararg keys: T) {
        require(keys.isNotEmpty()) { "Keys array must not be empty" }

        topLevelBackStacks[topLevelKey] = mutableStateListOf(*keys)
    }
}

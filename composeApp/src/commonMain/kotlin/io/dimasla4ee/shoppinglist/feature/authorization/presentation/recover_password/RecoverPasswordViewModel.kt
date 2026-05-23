package io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password

import io.dimasla4ee.shoppinglist.core.domain.interactor.RecoverPasswordUseCase
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel

@Suppress("ForbiddenComment")
class RecoverPasswordViewModel(
    private val recoverPasswordUseCase: RecoverPasswordUseCase
) : MviViewModel<RecoverPasswordIntent, RecoverPasswordState, RecoverPasswordEffect>(
    initialState = RecoverPasswordState()
) {

    override fun reduce(
        intent: RecoverPasswordIntent,
        current: RecoverPasswordState
    ): RecoverPasswordState = when (intent) {
        is RecoverPasswordIntent.Action -> current
    }

    override suspend fun handleIntent(intent: RecoverPasswordIntent) {
        val currentState = state.value
        val effect = when (intent) {
            RecoverPasswordIntent.Action.BackClicked -> RecoverPasswordEffect.NavigateBack
            RecoverPasswordIntent.Action.CancelClicked -> RecoverPasswordEffect.NavigateToSignIn
            RecoverPasswordIntent.Action.RecoverPasswordClicked ->
                handleRecoverPassword(currentState) ?: return
        }
        emitEffect(effect)
    }

    private suspend fun handleRecoverPassword(current: RecoverPasswordState): RecoverPasswordEffect? {
        val email = current.email.text.toString().trim()

        // TODO: Показывать snackbar / ошибки валидации
        if (email.isBlank() || !current.isRecoverEnabled) return null

        val result = recoverPasswordUseCase.invoke(email)

        return when (result) {
            is DomainResult.Error<*> -> null
            is DomainResult.Success<*> -> {
                // TODO: Показать сообщение об успешной отправке письма
                RecoverPasswordEffect.NavigateToSignIn
            }
        }
    }
}
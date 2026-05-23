package io.dimasla4ee.shoppinglist.feature.authorization.presentation.register

import io.dimasla4ee.shoppinglist.core.domain.interactor.auth.RegisterUseCase
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel

@Suppress("ForbiddenComment")
class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : MviViewModel<RegisterIntent, RegisterState, RegisterEffect>(
    initialState = RegisterState()
) {

    override fun reduce(
        intent: RegisterIntent,
        current: RegisterState
    ): RegisterState = when (intent) {
        is RegisterIntent.Action -> current

        RegisterIntent.UI.PasswordVisibilityToggled ->
            current.reducePasswordVisibilityToggled()

        RegisterIntent.UI.ConfirmPasswordVisibilityToggled ->
            current.reduceConfirmPasswordVisibilityToggled()
    }

    private fun RegisterState.reducePasswordVisibilityToggled(): RegisterState =
        copy(isPasswordVisible = !isPasswordVisible)

    private fun RegisterState.reduceConfirmPasswordVisibilityToggled(): RegisterState =
        copy(isConfirmPasswordVisible = !isConfirmPasswordVisible)

    override suspend fun handleIntent(intent: RegisterIntent) {
        val currentState = state.value
        val effect = when (intent) {
            is RegisterIntent.UI -> return
            RegisterIntent.Action.SignInClicked -> RegisterEffect.NavigateToSignIn
            RegisterIntent.Action.RegisterClicked -> handleRegister(currentState) ?: return
            RegisterIntent.Action.BackClicked -> RegisterEffect.NavigateBack
        }
        emitEffect(effect)
    }

    private suspend fun handleRegister(current: RegisterState): RegisterEffect? {
        val email = current.email.text.toString().trim()
        val password = current.password.text.toString()

        // TODO: Показывать snackbar / ошибки валидации
        if (email.isBlank() || password.isBlank() || !current.isRegisterAllowed) return null

        val result = registerUseCase.invoke(email, password)

        return when (result) {
            is DomainResult.Error<*> -> null
            is DomainResult.Success<*> -> RegisterEffect.NavigateToMain
        }
    }
}
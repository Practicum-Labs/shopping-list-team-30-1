package io.dimasla4ee.shoppinglist.feature.authorization.presentation.register

import io.dimasla4ee.shoppinglist.core.domain.interactor.RegisterUseCase
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import io.dimasla4ee.shoppinglist.utils.AppLog

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
        RegisterIntent.PasswordVisibilityToggleClicked -> {
            current.copy(
                isPasswordVisible = !current.isPasswordVisible
            )
        }

        RegisterIntent.RegisterClicked,
        RegisterIntent.SignInClicked -> current
    }

    override suspend fun handleIntent(intent: RegisterIntent) {
        val currentState = state.value

        AppLog.d("RegisterVM", state.value.toString())

        val effect = when (intent) {
            RegisterIntent.SignInClicked -> RegisterEffect.NavigateToSignIn
            RegisterIntent.RegisterClicked -> handleRegister(currentState) ?: return
            RegisterIntent.PasswordVisibilityToggleClicked -> return
        }

        emitEffect(effect)
    }

    private suspend fun handleRegister(current: RegisterState): RegisterEffect? {
        val email = current.email.text.toString().trim()
        val password = current.password.text.toString()

        // TODO: Показывать snackbar / ошибки валидации
        if (email.isBlank() || password.isBlank() || !current.isRegisterAllowed) return null

        val result = registerUseCase.invoke(email, password)

        AppLog.d("RegisterVM", result.toString())

        return when (result) {
            is DomainResult.Error<*> -> null
            is DomainResult.Success<*> -> RegisterEffect.NavigateToMain
        }
    }
}
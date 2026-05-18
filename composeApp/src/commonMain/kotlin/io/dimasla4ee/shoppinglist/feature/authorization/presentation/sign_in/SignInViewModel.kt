package io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in

import io.dimasla4ee.shoppinglist.core.domain.interactor.LoginUseCase
import io.dimasla4ee.shoppinglist.core.domain.model.DomainResult
import io.dimasla4ee.shoppinglist.core.mvi.MviViewModel
import io.dimasla4ee.shoppinglist.utils.AppLog

class SignInViewModel(
    val loginUseCase: LoginUseCase
) : MviViewModel<SignInIntent, SignInState, SignInEffect>(
    initialState = SignInState()
) {
    override fun reduce(
        intent: SignInIntent,
        current: SignInState
    ): SignInState = when (intent) {
        SignInIntent.PasswordVisibilityToggleClicked -> {
            current.copy(
                isPasswordVisible = !current.isPasswordVisible
            )
        }

        SignInIntent.ForgotPasswordClicked,
        SignInIntent.SignInClicked,
        SignInIntent.SignUpClicked -> current
    }

    override suspend fun handleIntent(intent: SignInIntent) {
        val currentState = state.value

        val effect = when (intent) {
            SignInIntent.ForgotPasswordClicked -> SignInEffect.NavigateToRecoverPassword
            SignInIntent.SignInClicked -> handleSignIn(currentState) ?: return
            SignInIntent.SignUpClicked -> SignInEffect.NavigateToRegister
            SignInIntent.PasswordVisibilityToggleClicked -> return
        }
        emitEffect(effect)
    }

    private suspend fun handleSignIn(current: SignInState): SignInEffect? {
        val email = current.email.text.toString()
        val password = current.password.text.toString()

        // TODO: Показывать снэкбар для обработки ошибок
        if (email.isBlank() || password.isBlank()) return null

        val result = loginUseCase.invoke(email, password)

        AppLog.d("VM", result.toString())
        return when (result) {
            is DomainResult.Error<*> -> null
            is DomainResult.Success<*> -> SignInEffect.NavigateToMain
        }
    }
}
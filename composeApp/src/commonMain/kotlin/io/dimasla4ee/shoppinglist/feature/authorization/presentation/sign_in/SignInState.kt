package io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in

import androidx.compose.foundation.text.input.TextFieldState
import io.dimasla4ee.shoppinglist.core.mvi.MviState
import io.dimasla4ee.shoppinglist.core.utils.isValidEmail

data class SignInState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false
) : MviState {
    val isSignInAllowed: Boolean
        get() = email.text.isValidEmail() && password.text.length >= PASSWORD_MIN_LENGTH

    companion object {
        private const val PASSWORD_MIN_LENGTH = 8
    }
}
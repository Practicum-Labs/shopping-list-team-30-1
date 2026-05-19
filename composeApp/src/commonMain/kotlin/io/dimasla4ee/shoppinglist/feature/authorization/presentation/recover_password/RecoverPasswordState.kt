package io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password

import androidx.compose.foundation.text.input.TextFieldState
import io.dimasla4ee.shoppinglist.core.mvi.MviState
import io.dimasla4ee.shoppinglist.core.utils.isValidEmail

data class RecoverPasswordState(
    val email: TextFieldState
) : MviState {
    val isRecoverEnabled: Boolean
        get() = email.text.isValidEmail()
}
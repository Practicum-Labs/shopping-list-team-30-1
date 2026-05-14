package io.dimasla4ee.shoppinglist.feature.authorization.presentation

import androidx.compose.foundation.text.input.TextFieldState
import io.dimasla4ee.shoppinglist.core.utils.isValidEmail

data class RecoverPasswordState(
    val email: TextFieldState
) {
    val isRecoverEnabled: Boolean
        get() = email.text.isValidEmail()
}
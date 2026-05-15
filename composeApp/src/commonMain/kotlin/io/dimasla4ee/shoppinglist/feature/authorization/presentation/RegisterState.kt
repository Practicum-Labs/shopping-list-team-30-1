package io.dimasla4ee.shoppinglist.feature.authorization.presentation

import androidx.compose.foundation.text.input.TextFieldState
import io.dimasla4ee.shoppinglist.core.mvi.MviState
import io.dimasla4ee.shoppinglist.core.utils.isValidEmail
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthLevel
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthResult

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordStrength: PasswordStrengthResult = PasswordStrengthResult(
        score = 0,
        level = PasswordStrengthLevel.Empty,
        isAcceptable = false
    )
) : MviState {
    val isRegisterAllowed: Boolean
        get() = passwordStrength.isAcceptable && email.text.isValidEmail()
}

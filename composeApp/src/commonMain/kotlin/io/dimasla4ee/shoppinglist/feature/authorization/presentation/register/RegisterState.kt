package io.dimasla4ee.shoppinglist.feature.authorization.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import io.dimasla4ee.shoppinglist.core.mvi.MviState
import io.dimasla4ee.shoppinglist.core.utils.isValidEmail
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordEstimator
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthMapper
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthResult

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false
) : MviState {
    private val passwordEstimator = PasswordEstimator()
    val passwordStrength: PasswordStrengthResult
        get() {
            val passwordStr = password.text.toString()
            return PasswordStrengthMapper.fromScore(
                score = passwordEstimator.estimate(passwordStr),
                password = passwordStr
            )
        }

    val isRegisterAllowed: Boolean
        get() = passwordStrength.isAcceptable && email.text.isValidEmail()
}
package io.dimasla4ee.shoppinglist.feature.authorization.ui.register

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.core.presentation.preview.AuthorizationPreviewHelper
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordEstimator
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthMapper
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterState

class RegisterInfoProvider : PreviewParameterProvider<RegisterState> {

    private val passwordEstimator = PasswordEstimator()

    override val values: Sequence<RegisterState>
        get() {
            val emails = AuthorizationPreviewHelper.emails
            val passwords = AuthorizationPreviewHelper.passwords
            val maxCombinations = maxOf(emails.size, passwords.size)

            return (0 until maxCombinations)
                .map { index ->
                    buildState(
                        email = emails[index % emails.size],
                        password = passwords[index % passwords.size],
                        isPasswordVisible = index % 2 != 0
                    )
                }
                .asSequence()
        }

    private fun buildState(
        email: String,
        password: String,
        isPasswordVisible: Boolean
    ): RegisterState {
        val rawScore = passwordEstimator.estimate(password)
        val passwordStrength = PasswordStrengthMapper.fromScore(
            score = rawScore,
            password = password
        )

        return RegisterState(
            email = TextFieldState(email),
            password = TextFieldState(password),
            isPasswordVisible = isPasswordVisible,
            passwordStrength = passwordStrength
        )
    }
}
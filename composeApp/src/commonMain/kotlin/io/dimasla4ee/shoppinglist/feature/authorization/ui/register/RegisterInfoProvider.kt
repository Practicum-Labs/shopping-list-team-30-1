package io.dimasla4ee.shoppinglist.feature.authorization.ui.register

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.core.presentation.preview.AuthorizationPreviewHelper
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.RegisterState

class RegisterInfoProvider : PreviewParameterProvider<RegisterState> {
    override val values: Sequence<RegisterState>
        get() {
            val emails = AuthorizationPreviewHelper.emails
            val passwords = AuthorizationPreviewHelper.passwords

            val maxCombinations = maxOf(emails.size, passwords.size)

            return (0 until maxCombinations).map { index ->
                RegisterState(
                    email = TextFieldState(emails[index % emails.size]),
                    password = TextFieldState(passwords[index % passwords.size]),
                    isPasswordVisible = index % 2 != 0
                )
            }.asSequence()
        }
}
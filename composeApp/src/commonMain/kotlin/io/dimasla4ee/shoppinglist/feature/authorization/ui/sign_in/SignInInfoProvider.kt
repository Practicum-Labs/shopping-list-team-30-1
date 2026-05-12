package io.dimasla4ee.shoppinglist.feature.authorization.ui.sign_in

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.core.presentation.preview.AuthorizationPreviewHelper
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.SignInState

class SignInInfoProvider : PreviewParameterProvider<SignInState> {
    override val values: Sequence<SignInState>
        get() {
            val emails = AuthorizationPreviewHelper.emails
            val passwords = AuthorizationPreviewHelper.passwords

            val maxCombinations = maxOf(emails.size, passwords.size)

            return (0 until maxCombinations).map { index ->
                SignInState(
                    email = TextFieldState(emails[index % emails.size]),
                    password = TextFieldState(passwords[index % passwords.size]),
                    isPasswordVisible = index % 2 != 0
                )
            }.asSequence()
        }
}
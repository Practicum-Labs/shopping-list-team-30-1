package io.dimasla4ee.shoppinglist.feature.authorization.ui

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.core.mvi.MviState
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthLevel
import io.dimasla4ee.shoppinglist.feature.authorization.domain.password_strength_meter.PasswordStrengthResult
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordState
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterState
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInState

class AuthorizationScreenPreviewProvider : PreviewParameterProvider<AuthorizationScreenType> {
    override val values: Sequence<AuthorizationScreenType> = sequenceOf(
        AuthorizationScreenType.SignIn,
        AuthorizationScreenType.Register,
        AuthorizationScreenType.RecoverPassword
    )
}

sealed class AuthorizationScreenType(
    open val state: MviState
) {
    data object SignIn : AuthorizationScreenType(
        state = SignInState(
            email = TextFieldState("test@example.com"),
            password = TextFieldState("password123"),
            isPasswordVisible = false
        )
    )

    data object Register : AuthorizationScreenType(
        state = RegisterState(
            email = TextFieldState("new_user@example.com"),
            password = TextFieldState("Str0ng!Pass"),
            isPasswordVisible = false,
            passwordStrength = PasswordStrengthResult(
                score = 3,
                level = PasswordStrengthLevel.Strong,
                isAcceptable = true
            )
        )
    )

    data object RecoverPassword : AuthorizationScreenType(
        state = RecoverPasswordState(
            email = TextFieldState("forgot@example.com")
        )
    )
}
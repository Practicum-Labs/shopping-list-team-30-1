package io.dimasla4ee.shoppinglist.feature.authorization.presentation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SignInInfoProvider : PreviewParameterProvider<SignInState> {
    override val values: Sequence<SignInState>
        get() = sequenceOf(
            SignInState(),
            SignInState(TextFieldState("de.krylov@vk.com")),
            SignInState(TextFieldState("de.krylov@vk.com"), TextFieldState("password")),
            SignInState(TextFieldState("de.krylov@vk.com"), TextFieldState("password"), true),
        )
}
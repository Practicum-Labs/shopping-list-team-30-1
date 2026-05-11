package io.dimasla4ee.shoppinglist.feature.authorization.presentation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class RegisterInfoProvider : PreviewParameterProvider<RegisterState> {
    override val values: Sequence<RegisterState>
        get() = sequenceOf(
            RegisterState(),
            RegisterState(TextFieldState("de.krylov@vk.com")),
            RegisterState(TextFieldState("de.krylov@vk.com"), TextFieldState("password")),
            RegisterState(TextFieldState("de.krylov@vk.com"), TextFieldState("password"), true),
        )

}
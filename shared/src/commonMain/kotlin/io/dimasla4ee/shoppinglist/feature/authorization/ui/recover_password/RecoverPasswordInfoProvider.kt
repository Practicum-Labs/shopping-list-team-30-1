package io.dimasla4ee.shoppinglist.feature.authorization.ui.recover_password

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordState

class RecoverPasswordInfoProvider : PreviewParameterProvider<RecoverPasswordState> {
    override val values: Sequence<RecoverPasswordState>
        get() = sequenceOf(
            RecoverPasswordState(TextFieldState()),
            RecoverPasswordState(TextFieldState("example")),
            RecoverPasswordState(TextFieldState("example@example.com"))
        )
}
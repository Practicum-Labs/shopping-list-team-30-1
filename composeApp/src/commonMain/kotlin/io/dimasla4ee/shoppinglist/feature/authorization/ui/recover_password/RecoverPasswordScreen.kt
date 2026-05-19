package io.dimasla4ee.shoppinglist.feature.authorization.ui.recover_password

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordIntent
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordState
import io.dimasla4ee.shoppinglist.feature.authorization.ui.AuthorizationScreen

@Composable
fun RecoverPasswordScreen(
    state: RecoverPasswordState,
    onIntent: (RecoverPasswordIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    AuthorizationScreen(
        modifier = modifier.fillMaxSize()
    ) {
        RecoverPasswordContent(
            state = state,
            onRecoverPassword = {
                onIntent(RecoverPasswordIntent.RecoverPasswordClicked)
            },
            onCancel = {
                onIntent(RecoverPasswordIntent.CancelClicked)
            },
            modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
        )
    }
}
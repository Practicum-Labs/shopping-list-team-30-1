package io.dimasla4ee.shoppinglist.feature.authorization.ui.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterIntent
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterState
import io.dimasla4ee.shoppinglist.feature.authorization.ui.AuthorizationScreen

@Composable
fun RegisterScreen(
    state: RegisterState,
    onIntent: (RegisterIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    AuthorizationScreen(
        modifier = modifier.fillMaxSize()
    ) {
        RegisterContent(
            state = state,
            onShowPassword = {
                onIntent(RegisterIntent.PasswordVisibilityToggleClicked)
            },
            onRegister = {
                onIntent(RegisterIntent.RegisterClicked)
            },
            onAuthorization = {
                onIntent(RegisterIntent.SignInClicked)
            },
            modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
        )
    }
}
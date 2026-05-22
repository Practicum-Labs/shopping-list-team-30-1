package io.dimasla4ee.shoppinglist.feature.authorization.ui.sign_in

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInIntent
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInState
import io.dimasla4ee.shoppinglist.feature.authorization.ui.AuthorizationScreen

@Composable
fun SignInScreen(
    state: SignInState,
    onIntent: (SignInIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    AuthorizationScreen(
        modifier = modifier.fillMaxSize(),
        onBackClick = { TODO("Navigate back")}
    ) {
        SignInContent(
            state = state,
            onShowPassword = {
                onIntent(SignInIntent.PasswordVisibilityToggleClicked)
            },
            onSignIn = {
                onIntent(SignInIntent.SignInClicked)
            },
            onForgotPassword = {
                onIntent(SignInIntent.ForgotPasswordClicked)
            },
            onRegistration = {
                onIntent(SignInIntent.SignUpClicked)
            },
            onContinueAsGuest = {
                onIntent(SignInIntent.ContinueAsGuestClicked)
            },
            modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
        )
    }
}
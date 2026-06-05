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
        onBackClick = { onIntent(SignInIntent.Action.BackClicked) }
    ) {
        SignInContent(
            state = state,
            onShowPassword =
                { onIntent(SignInIntent.UI.PasswordVisibilityToggleClicked) },
            onSignIn = { onIntent(SignInIntent.Action.SignInClicked) },
            onForgotPassword = { onIntent(SignInIntent.Action.ForgotPasswordClicked) },
            onRegistration = { onIntent(SignInIntent.Action.SignUpClicked) },
            onContinueAsGuest = { onIntent(SignInIntent.Action.ContinueAsGuestClicked) },
            modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
        )
    }
}
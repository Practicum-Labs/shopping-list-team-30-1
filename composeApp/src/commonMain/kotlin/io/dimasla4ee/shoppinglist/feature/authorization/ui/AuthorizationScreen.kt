package io.dimasla4ee.shoppinglist.feature.authorization.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordState
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterState
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInState
import io.dimasla4ee.shoppinglist.feature.authorization.ui.recover_password.RecoverPasswordContent
import io.dimasla4ee.shoppinglist.feature.authorization.ui.register.RegisterContent
import io.dimasla4ee.shoppinglist.feature.authorization.ui.sign_in.SignInContent
import io.dimasla4ee.shoppinglist.feature.welcome_screen.ui.createWelcomeLogo

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val (annotatedString, inlineContentMap) = createWelcomeLogo()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(vertical = AppDimensions.paddingLarge),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    inlineContent = inlineContentMap,
                    text = annotatedString,
                    style = MaterialTheme.typography.titleLargeEmphasized,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = maxHeight)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun AuthorizationScreenPreview(
    @PreviewParameter(AuthorizationScreenPreviewProvider::class) screenType: AuthorizationScreenType
) = ShoppingListTheme {
    AuthorizationScreen(Modifier.fillMaxSize()) {
        when (screenType) {
            AuthorizationScreenType.RecoverPassword -> {
                RecoverPasswordContent(
                    state = screenType.state as RecoverPasswordState,
                    onRecoverPassword = {},
                    onCancel = {},
                    modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
                )
            }

            AuthorizationScreenType.Register -> {
                RegisterContent(
                    state = screenType.state as RegisterState,
                    onShowPassword = {},
                    onRegister = {},
                    onAuthorization = {},
                    modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
                )
            }

            AuthorizationScreenType.SignIn -> {
                SignInContent(
                    state = screenType.state as SignInState,
                    onShowPassword = {},
                    onSignIn = {},
                    onContinueAsGuest = {},
                    onForgotPassword = {},
                    onRegistration = {},
                    modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
                )
            }
        }
    }
}
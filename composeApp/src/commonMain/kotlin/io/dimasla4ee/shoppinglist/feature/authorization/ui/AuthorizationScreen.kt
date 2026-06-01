package io.dimasla4ee.shoppinglist.feature.authorization.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListLogo
import io.dimasla4ee.shoppinglist.core.presentation.components.topbar.AppTopBarDefaults
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordState
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterState
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInState
import io.dimasla4ee.shoppinglist.feature.authorization.ui.recover_password.RecoverPasswordContent
import io.dimasla4ee.shoppinglist.feature.authorization.ui.register.RegisterContent
import io.dimasla4ee.shoppinglist.feature.authorization.ui.sign_in.SignInContent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_back
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AuthorizationScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_back_24),
                            contentDescription = stringResource(Res.string.content_back)
                        )
                    }
                },
                colors = AppTopBarDefaults.productsTopBarColors()
            )

        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .defaultMinSize(minHeight = maxHeight),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShoppingListLogo({})

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    contentAlignment = Alignment.Center
                ) {
                    content()
                }
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
    AuthorizationScreen(
        modifier = Modifier.fillMaxSize(),
        onBackClick = {}
    ) {
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
                    onShowConfirmPassword = {},
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

package io.dimasla4ee.shoppinglist.feature.authorization.ui.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions.SignInDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.AppOutlinedPasswordTextField
import io.dimasla4ee.shoppinglist.core.presentation.components.AppOutlinedTextField
import io.dimasla4ee.shoppinglist.core.presentation.components.buttons.AppTextButton
import io.dimasla4ee.shoppinglist.core.presentation.preview.CenterAlignedBoxWithSystemPaddings
import io.dimasla4ee.shoppinglist.core.utils.appDefaultFormSize
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInState
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.authorization_button
import shoppinglist.composeapp.generated.resources.authorization_continue_as_guest
import shoppinglist.composeapp.generated.resources.authorization_email_hint
import shoppinglist.composeapp.generated.resources.authorization_email_label
import shoppinglist.composeapp.generated.resources.authorization_forgot_password
import shoppinglist.composeapp.generated.resources.authorization_no_account
import shoppinglist.composeapp.generated.resources.authorization_password_hint
import shoppinglist.composeapp.generated.resources.authorization_password_label
import shoppinglist.composeapp.generated.resources.authorization_sign_in

@Composable
fun SignInContent(
    state: SignInState,
    onShowPassword: () -> Unit,
    onSignIn: () -> Unit,
    onForgotPassword: () -> Unit,
    onRegistration: () -> Unit,
    onContinueAsGuest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SignInDimensions.ContentSpacing)
    ) {
        AppOutlinedTextField(
            state = state.email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = stringResource(Res.string.authorization_email_hint),
            label = stringResource(Res.string.authorization_email_label)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.appDefaultFormSize()
        ) {
            AppOutlinedPasswordTextField(
                state = state.password,
                label = stringResource(Res.string.authorization_password_label),
                placeholder = stringResource(Res.string.authorization_password_hint),
                isPasswordVisible = state.isPasswordVisible,
                onShowPassword = onShowPassword
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppTextButton(
                    text = stringResource(Res.string.authorization_continue_as_guest),
                    textStyle = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.None,
                    onClick = onContinueAsGuest
                )
                AppTextButton(
                    text = stringResource(Res.string.authorization_forgot_password),
                    textStyle = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.None,
                    onClick = onForgotPassword
                )
            }
        }

        Button(
            modifier = Modifier
                .appDefaultFormSize()
                .height(SignInDimensions.ButtonHeight),
            onClick = onSignIn,
            shape = RoundedCornerShape(SignInDimensions.ButtonCornerRadius)
        ) {
            Text(stringResource(Res.string.authorization_sign_in))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = SignInDimensions.FooterTopPadding)
        ) {
            Text(stringResource(Res.string.authorization_no_account))
            AppTextButton(
                text = stringResource(Res.string.authorization_button),
                onClick = onRegistration
            )
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun PreviewSignInContent(
    @PreviewParameter(SignInInfoProvider::class) state: SignInState
) = ShoppingListTheme {
    var localState by remember { mutableStateOf(state) }

    CenterAlignedBoxWithSystemPaddings {
        SignInContent(
            state = localState,
            onSignIn = {},
            onRegistration = {},
            onForgotPassword = {},
            onContinueAsGuest = {},
            onShowPassword = {
                localState = localState.copy(
                    isPasswordVisible = !localState.isPasswordVisible
                )
            },
            modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
        )
    }
}
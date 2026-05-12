package io.dimasla4ee.shoppinglist.feature.authorization.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions.RegisterDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.AppOutlinedPasswordTextField
import io.dimasla4ee.shoppinglist.core.presentation.components.AppOutlinedTextField
import io.dimasla4ee.shoppinglist.core.presentation.preview.CenterAlignedBoxWithSystemPaddings
import io.dimasla4ee.shoppinglist.core.utils.appDefaultFormSize
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.RegisterState
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.authorization_button
import shoppinglist.composeapp.generated.resources.authorization_email_hint
import shoppinglist.composeapp.generated.resources.authorization_email_label
import shoppinglist.composeapp.generated.resources.authorization_have_account
import shoppinglist.composeapp.generated.resources.authorization_password_hint
import shoppinglist.composeapp.generated.resources.authorization_password_label
import shoppinglist.composeapp.generated.resources.authorization_sign_in

@Composable
fun RegisterContent(
    state: RegisterState,
    onShowPassword: () -> Unit,
    onRegister: () -> Unit,
    onAuthorization: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(RegisterDimensions.ContentSpacing)
    ) {
        AppOutlinedTextField(
            state = state.email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = stringResource(Res.string.authorization_email_hint),
            label = stringResource(Res.string.authorization_email_label)
        )

        Column(modifier = Modifier.appDefaultFormSize()) {
            AppOutlinedPasswordTextField(
                state = state.password,
                label = stringResource(Res.string.authorization_password_label),
                placeholder = stringResource(Res.string.authorization_password_hint),
                isPasswordVisible = state.isPasswordVisible,
                onShowPassword = onShowPassword
            )

            PasswordStrengthMeter(
                level = state.passwordStrength.level,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
            )
        }

        Button(
            modifier = Modifier
                .appDefaultFormSize()
                .height(RegisterDimensions.ButtonHeight),
            onClick = onRegister,
            shape = RoundedCornerShape(RegisterDimensions.ButtonCornerRadius)
        ) {
            Text(stringResource(Res.string.authorization_button))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = RegisterDimensions.FooterTopPadding)
        ) {
            Text(stringResource(Res.string.authorization_have_account))
            TextButton(
                onClick = onAuthorization,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = stringResource(Res.string.authorization_sign_in),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun PreviewRegisterContent(
    @PreviewParameter(RegisterInfoProvider::class) state: RegisterState
) = ShoppingListTheme {
    var localState by remember { mutableStateOf(state) }

    CenterAlignedBoxWithSystemPaddings {
        RegisterContent(
            state = localState,
            onRegister = {},
            onAuthorization = {},
            onShowPassword = {
                localState = localState.copy(
                    isPasswordVisible = !localState.isPasswordVisible
                )
            },
            modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
        )
    }
}


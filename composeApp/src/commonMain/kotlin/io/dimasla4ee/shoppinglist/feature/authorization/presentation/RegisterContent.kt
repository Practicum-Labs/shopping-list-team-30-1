package io.dimasla4ee.shoppinglist.feature.authorization.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.cd_hide_password
import shoppinglist.composeapp.generated.resources.cd_show_password
import shoppinglist.composeapp.generated.resources.ic_visibility_off_24
import shoppinglist.composeapp.generated.resources.ic_visibility_on_24
import shoppinglist.composeapp.generated.resources.register_button
import shoppinglist.composeapp.generated.resources.register_email_hint
import shoppinglist.composeapp.generated.resources.register_email_label
import shoppinglist.composeapp.generated.resources.register_have_account
import shoppinglist.composeapp.generated.resources.register_password_hint
import shoppinglist.composeapp.generated.resources.register_password_label
import shoppinglist.composeapp.generated.resources.register_sign_in

@Composable
fun RegisterContent(
    state: RegisterState,
    onShowPassword: () -> Unit,
    onRegister: () -> Unit,
    onAuthorization: () -> Unit,
    modifier: Modifier = Modifier
) {
    val componentModifier = modifier
        .widthIn(0.dp, 350.dp)
        .fillMaxWidth()
        .wrapContentHeight()

    val (visibilityIconRes, visibilityContentDescriptionRes) = when(state.isPasswordVisible) {
        true -> Res.drawable.ic_visibility_off_24 to Res.string.cd_hide_password
        false -> Res.drawable.ic_visibility_on_24 to Res.string.cd_show_password
    }

    Column(
        modifier = componentModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = componentModifier,
            state = state.email,
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary
            ),
            placeholder = { Text(stringResource(Res.string.register_email_hint)) },
            label = { Text(stringResource(Res.string.register_email_label)) }
        )
        OutlinedSecureTextField(
            modifier = componentModifier,
            state = state.password,
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textObfuscationMode = when (state.isPasswordVisible) {
                true -> TextObfuscationMode.Visible
                false -> TextObfuscationMode.Hidden
            },
            trailingIcon = {
                IconButton(
                    onClick = onShowPassword
                ) {
                    Icon(
                        painter = painterResource(visibilityIconRes),
                        contentDescription = stringResource(visibilityContentDescriptionRes)
                    )
                }
            },
            label = { Text(stringResource(Res.string.register_password_label)) },
            placeholder = { Text(stringResource(Res.string.register_password_hint)) }
        )
        Button(
            modifier = componentModifier.height(56.dp),
            onClick = onRegister,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(stringResource(Res.string.register_button))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(stringResource(Res.string.register_have_account))
            TextButton(
                onClick = onAuthorization,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = stringResource(Res.string.register_sign_in),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview
@PreviewLightDark
@Composable
fun PreviewRegisterContent(
    @PreviewParameter(RegisterInfoProvider::class) state: RegisterState
) {
    var localState by remember { mutableStateOf(state) }

    ShoppingListTheme {
        Scaffold(Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                RegisterContent(
                    state = localState,
                    onRegister = {},
                    onAuthorization = {},
                    onShowPassword = {
                        localState = localState.copy(
                            isPasswordVisible = !localState.isPasswordVisible
                        )
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import io.dimasla4ee.shoppinglist.core.utils.appDefaultFormSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.cd_hide_password
import shoppinglist.composeapp.generated.resources.cd_show_password
import shoppinglist.composeapp.generated.resources.ic_visibility_off_24
import shoppinglist.composeapp.generated.resources.ic_visibility_on_24

@Composable
fun AppOutlinedTextField(
    state: TextFieldState,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) = OutlinedTextField(
    modifier = modifier.appDefaultFormSize(),
    state = state,
    colors = AppOutlinedTextFieldDefaults.colors(),
    placeholder = { Text(placeholder) },
    label = { Text(label) },
    keyboardOptions = keyboardOptions
)

@Composable
fun AppOutlinedPasswordTextField(
    state: TextFieldState,
    isPasswordVisible: Boolean,
    placeholder: String,
    label: String,
    modifier: Modifier = Modifier,
    onShowPassword: () -> Unit
) {
    val iconRes =
        if (isPasswordVisible) Res.drawable.ic_visibility_off_24 else Res.drawable.ic_visibility_on_24
    val contentDescriptionRes =
        if (isPasswordVisible) Res.string.cd_hide_password else Res.string.cd_show_password
    val textObfuscationMode =
        if (isPasswordVisible) TextObfuscationMode.Visible else TextObfuscationMode.Hidden

    OutlinedSecureTextField(
        modifier = modifier.appDefaultFormSize(),
        state = state,
        colors = AppOutlinedTextFieldDefaults.colors(),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        textObfuscationMode = textObfuscationMode,
        trailingIcon = {
            IconButton(onClick = onShowPassword) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = stringResource(contentDescriptionRes)
                )
            }
        }
    )
}
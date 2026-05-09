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
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.mvi.MviState
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_add_circle_24
import shoppinglist.composeapp.generated.resources.ic_remove_circle_24

@Composable
fun RegisterContent(
    state: RegisterState,
    onShowPassword: () -> Unit,
    onRegister: () -> Unit,
    onAuthorization: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .widthIn(0.dp, 350.dp)
                .fillMaxWidth(),
            state = state.email,
            label = {
                Text("Введите Email")
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .widthIn(0.dp, 350.dp)
                .fillMaxWidth(),
            state = state.password,
            trailingIcon = {
                IconButton(
                    onClick = onShowPassword
                ) {
                    val iconRes = when(state.isPasswordVisible) {
                        true -> Res.drawable.ic_add_circle_24       // TODO
                        false -> Res.drawable.ic_remove_circle_24   // TODO
                    }
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = null                   // TODO
                    )
                }
            },
            label = {
                Text("Введите пароль")
            }
        )
        Button(
            modifier = Modifier
                .height(56.dp)
                .widthIn(0.dp, 350.dp)
                .fillMaxWidth(),
            onClick = onRegister,
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Зарегистрироваться")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Уже есть аккаунт?")
            TextButton(
                onClick = onAuthorization
            ) {
                Text(
                    text = "Войти",
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false
) : MviState

class RegisterInfoProvider : PreviewParameterProvider<RegisterState> {
    override val values: Sequence<RegisterState>
        get() = sequenceOf(
            RegisterState(),
            RegisterState(TextFieldState("de.krylov@vk.com")),
            RegisterState(TextFieldState("de.krylov@vk.com"), TextFieldState("password")),
            RegisterState(TextFieldState("de.krylov@vk.com"), TextFieldState("password"), true),
        )

}

@Preview
@PreviewLightDark
@Composable
fun PreviewRegisterContent(
    @PreviewParameter(RegisterInfoProvider::class) state: RegisterState
) {
    ShoppingListTheme {
        Scaffold(Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                RegisterContent(
                    state = state,
                    onRegister = {},
                    onAuthorization = {},
                    onShowPassword = {},
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
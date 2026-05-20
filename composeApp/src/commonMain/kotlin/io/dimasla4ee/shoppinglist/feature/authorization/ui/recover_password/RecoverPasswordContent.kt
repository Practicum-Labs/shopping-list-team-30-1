package io.dimasla4ee.shoppinglist.feature.authorization.ui.recover_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions.SignInDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.AppOutlinedTextField
import io.dimasla4ee.shoppinglist.core.presentation.components.buttons.AppTextButton
import io.dimasla4ee.shoppinglist.core.presentation.preview.CenterAlignedBoxWithSystemPaddings
import io.dimasla4ee.shoppinglist.core.utils.appDefaultFormSize
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordState
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.authorization_email_hint
import shoppinglist.composeapp.generated.resources.authorization_email_label
import shoppinglist.composeapp.generated.resources.authorization_recover_password_button
import shoppinglist.composeapp.generated.resources.authorization_recover_password_cancel
import shoppinglist.composeapp.generated.resources.authorization_recover_password_description
import shoppinglist.composeapp.generated.resources.authorization_recover_password_title

@Composable
fun RecoverPasswordContent(
    state: RecoverPasswordState,
    onRecoverPassword: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SignInDimensions.ContentSpacing)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimensions.paddingSmall)
        ) {
            Text(
                text = stringResource(Res.string.authorization_recover_password_title),
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                modifier = Modifier.appDefaultFormSize(),
                text = stringResource(Res.string.authorization_recover_password_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        AppOutlinedTextField(
            state = state.email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = stringResource(Res.string.authorization_email_hint),
            label = stringResource(Res.string.authorization_email_label)
        )

        Button(
            modifier = Modifier
                .appDefaultFormSize()
                .height(SignInDimensions.ButtonHeight),
            onClick = onRecoverPassword,
            shape = RoundedCornerShape(SignInDimensions.ButtonCornerRadius),
            enabled = state.isRecoverEnabled
        ) {
            Text(stringResource(Res.string.authorization_recover_password_button))
        }

        AppTextButton(
            text = stringResource(Res.string.authorization_recover_password_cancel),
            onClick = onCancel,
            textDecoration = TextDecoration.None
        )
    }
}

@Preview
@PreviewLightDark
@Composable
private fun RecoverPasswordContentPreview(
    @PreviewParameter(RecoverPasswordInfoProvider::class) state: RecoverPasswordState
) = ShoppingListTheme {
    CenterAlignedBoxWithSystemPaddings {
        RecoverPasswordContent(
            state = state,
            onRecoverPassword = {},
            onCancel = {},
            modifier = Modifier.padding(horizontal = AppDimensions.paddingMedium)
        )
    }
}


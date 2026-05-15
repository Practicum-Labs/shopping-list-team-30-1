package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.DialogStyle
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.defaultDialogButtonColors
import io.dimasla4ee.shoppinglist.app.ui.theme.dialogTextFieldColors
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.components.DialogButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.button_cancel
import shoppinglist.composeapp.generated.resources.button_create
import shoppinglist.composeapp.generated.resources.dialog_list_add
import shoppinglist.composeapp.generated.resources.hint_name_list
import shoppinglist.composeapp.generated.resources.ic_docs_add_24

@Composable
fun CreateListDialog(
    name: String,
    onNameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = DialogStyle.containerColor(),
        shape = DialogStyle.shape(),

        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(AppDimensions.DialogAddition.spaceIconTextField)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_docs_add_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = stringResource(Res.string.dialog_list_add),
                    style = AppTypography.titleLarge
                )
            }
        },

        text = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = onNameChange,
                singleLine = true,

                label = {
                    Text(
                        text = stringResource(Res.string.hint_name_list),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },

                colors = dialogTextFieldColors()
            )
        },

        confirmButton = {
            DialogButton(
                text = stringResource(Res.string.button_create),
                onClick = onConfirm,
                enabled = name.isNotBlank(),
                colors = defaultDialogButtonColors()
            )
        },
        dismissButton = {
            DialogButton(
                text = stringResource(Res.string.button_cancel),
                onClick = onDismiss,
                colors = defaultDialogButtonColors()
            )
        },
    )
}

@Preview
@PreviewLightDark
@Composable
private fun CreateListDialogAllPreview() {
    var text by remember { mutableStateOf("") }

    ShoppingListTheme {
        CreateListDialog(
            name = text,
            onNameChange = { text = it },
            onDismiss = {},
            onConfirm = {}
        )
    }
}
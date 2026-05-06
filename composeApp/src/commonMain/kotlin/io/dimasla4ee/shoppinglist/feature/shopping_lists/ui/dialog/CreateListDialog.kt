package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.dialogTextFieldColors
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

        // фон диалога
        containerColor = MaterialTheme.colorScheme.tertiary,

        // скругление углов
        shape = RoundedCornerShape(AppDimensions.DialogAddition.cornerRadius),

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
            TextButton(
                onClick = onConfirm,
                enabled = name.isNotBlank(),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Text(
                    text = stringResource(Res.string.button_create),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },

        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Text(
                    text = stringResource(Res.string.button_cancel),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },
    )
}

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
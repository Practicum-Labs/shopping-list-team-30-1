package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.DialogStyle
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.deleteDialogButtonCancel
import io.dimasla4ee.shoppinglist.app.ui.theme.deleteDialogButtonDelete
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.components.DialogButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.button_cancel
import shoppinglist.shared.generated.resources.button_delete
import shoppinglist.shared.generated.resources.dialog_delete_list
import shoppinglist.shared.generated.resources.ic_warning_24

@Composable
fun DeleteListDialog(
    listName: String,
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
                    painter = painterResource(Res.drawable.ic_warning_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                val template = stringResource(Res.string.dialog_delete_list)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = template.format(listName),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
        },

        confirmButton = {
            DialogButton(
                text = stringResource(Res.string.button_delete),
                onClick = onConfirm,
                colors = deleteDialogButtonDelete()
            )
        },
        dismissButton = {
            DialogButton(
                text = stringResource(Res.string.button_cancel),
                onClick = onDismiss,
                colors = deleteDialogButtonCancel()
            )
        },
    )
}

@Preview
@PreviewLightDark
@Composable
private fun CreateListDialogAllPreview() {
    ShoppingListTheme {
        DeleteListDialog(
            listName = "Мыльнорыльное",
            onDismiss = {},
            onConfirm = {}
        )
    }
}
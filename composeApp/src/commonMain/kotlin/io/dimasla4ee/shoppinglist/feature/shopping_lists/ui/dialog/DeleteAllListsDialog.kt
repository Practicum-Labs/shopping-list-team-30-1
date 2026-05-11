package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.AppTypography
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.deleteDialogButtonCancel
import io.dimasla4ee.shoppinglist.app.ui.theme.deleteDialogButtonDelete
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.components.DialogButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.button_cancel
import shoppinglist.composeapp.generated.resources.button_delete
import shoppinglist.composeapp.generated.resources.dialog_delete_all_lists
import shoppinglist.composeapp.generated.resources.ic_warning_24

@Composable
fun DeleteAllListsDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(AppDimensions.DialogAddition.cornerRadius),
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

                Text(
                    text = stringResource(Res.string.dialog_delete_all_lists),
                    style = AppTypography.titleLarge
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
        DeleteAllListsDialog(
            onDismiss = {},
            onConfirm = {}
        )
    }
}
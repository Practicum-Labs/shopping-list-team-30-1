package io.dimasla4ee.shoppinglist.feature.products_screen.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.btm_menu_clear_purchased_items
import shoppinglist.composeapp.generated.resources.btm_menu_delete_all
import shoppinglist.composeapp.generated.resources.ic_clear_24
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_swap_vert_24
import shoppinglist.composeapp.generated.resources.sort_alphabetical
import shoppinglist.composeapp.generated.resources.sort_custom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsMenuBottomSheet(
    visible: Boolean,
    sortMode: SortMode,
    onDismiss: () -> Unit,
    onSortClick: () -> Unit,
    onDeleteAllClick: () -> Unit,
    onDeleteCheckClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (visible) {
        ModalBottomSheet(
            modifier = modifier
                .padding(horizontal = 6.dp),
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) {

            Column(modifier = modifier.fillMaxWidth()) {

                MenuItem(
                    icon = painterResource(Res.drawable.ic_swap_vert_24),
                    text = when (sortMode) {
                        SortMode.CUSTOM -> stringResource(Res.string.sort_custom)
                        SortMode.ALPHABETICAL -> stringResource(Res.string.sort_alphabetical)
                    },
                    onClick = onSortClick
                )

                MenuItem(
                    icon = painterResource(Res.drawable.ic_delete_24),
                    text = stringResource(Res.string.btm_menu_delete_all),
                    onClick = onDeleteAllClick
                )

                MenuItem(
                    icon = painterResource(Res.drawable.ic_clear_24),
                    text = stringResource(Res.string.btm_menu_clear_purchased_items),
                    onClick = onDeleteCheckClick
                )
            }

        }
    }
}


@Preview
@PreviewLightDark
@Composable
private fun ProductsMenuBottomSheetPreview() {
    ShoppingListTheme {
        ProductsMenuBottomSheet(
            visible = true,
            sortMode = SortMode.CUSTOM,
            onDismiss = {},
            onSortClick = {},
            onDeleteAllClick = {},
            onDeleteCheckClick = {},
        )
    }
}
package io.dimasla4ee.shoppinglist.feature.products_screen.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.btm_menu_clear_purchased_items
import shoppinglist.shared.generated.resources.btm_menu_delete_all
import shoppinglist.shared.generated.resources.cd_delete_list
import shoppinglist.shared.generated.resources.ic_clear_24
import shoppinglist.shared.generated.resources.ic_delete_24

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsMenuBottomSheet(
    visible: Boolean,
    sortMode: SortMode,
    onDismiss: () -> Unit,
    onSortClick: (SortMode) -> Unit,
    onDeleteAllClick: () -> Unit,
    onDeleteCheckClick: () -> Unit,
    onDeleteList: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (visible) {
        ModalBottomSheet(
            modifier = modifier.padding(horizontal = 6.dp),
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) {
            Column(modifier = modifier.fillMaxWidth()) {
                SortSelector(
                    sortMode = sortMode,
                    onSortSelect = { mode -> onSortClick(mode) }
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

                HorizontalDivider(
                    Modifier
                        .height(2.dp)
                        .background(MaterialTheme.colorScheme.tertiary)
                )

                MenuItem(
                    icon = painterResource(Res.drawable.ic_delete_24),
                    text = stringResource(Res.string.cd_delete_list),
                    onClick = onDeleteList,
                    contentColor = Color.Red
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
            onDeleteList = {}
        )
    }
}
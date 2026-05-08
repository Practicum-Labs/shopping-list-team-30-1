package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListsScaffold
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarAction
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListCardEvent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsState
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.bottom_sheet.IconPickerBottomSheet
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.CreateListDialog

@Composable
fun ShoppingListsScreenContent(
    state: ShoppingListsState,
    onFabClick: () -> Unit,
    onEvent: (ShoppingListCardEvent) -> Unit,
    onNameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    ShoppingListsScaffold(
        modifier = modifier,
        title = "Мои списки",

        action1 = TopBarAction("Search") {},
        action2 = TopBarAction("Delete") {},
        action3 = TopBarAction("Theme") {},

        onFabClick = onFabClick
    ) { padding ->

        if (state.lists.isEmpty()) {
            ShoppingListsEmptyState(
                modifier = Modifier.padding(padding)
            )
        } else {
            ShoppingListsContent(
                lists = state.lists,
                onEvent = onEvent,
                modifier = Modifier.padding(padding)
            )
        }
    }

    if (state.isDialogVisible) {
        CreateListDialog(
            name = state.newListName,
            onNameChange = onNameChange,
            onDismiss = onDismiss,
            onConfirm = onConfirm
        )
    }

    if (state.isIconSheetVisible) {
        IconPickerBottomSheet(
            selectedIcon = null,
            onIconSelect = {},
            onDismiss = onDismiss
        )
    }
}

@Preview
@PreviewLightDark
@Composable
private fun ShoppingListsScreenPreview(
    @PreviewParameter(ShoppingListsStateProvider::class)
    state: ShoppingListsState
) {
    ShoppingListTheme {
        ShoppingListsScreenContent(
            state = state,
            onFabClick = {},
            onEvent = {},
            onNameChange = {},
            onDismiss = {},
            onConfirm = {}
        )
    }
}

@Preview
@PreviewLightDark
@Composable
private fun ShoppingListsScreenBottomSheetPreview() {

    ShoppingListTheme {
        ShoppingListsScreenContent(
            state = ShoppingListsState(
                lists = listOf(
                    ShoppingList(
                        id = 1,
                        name = "Продукты",
                        icon = ShoppingListIcon.SHOPPING_CART,
                        products = emptyList()
                    )
                ),
                isIconSheetVisible = true,
                selectedListId = 1
            ),
            onFabClick = {},
            onEvent = {},
            onNameChange = {},
            onDismiss = {},
            onConfirm = {}
        )
    }
}
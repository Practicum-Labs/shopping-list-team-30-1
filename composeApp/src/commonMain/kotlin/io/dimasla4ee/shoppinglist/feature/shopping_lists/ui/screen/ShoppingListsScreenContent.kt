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
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteAllListsDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.RenameListDialog
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.screen_title

@Composable
fun ShoppingListsScreenContent(
    state: ShoppingListsState,
    visibleLists: List<ShoppingList>,
    onFabClick: () -> Unit,
    onEvent: (ShoppingListCardEvent) -> Unit,
    onNameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onIconSelect: (ShoppingListIcon) -> Unit,
    onSheetDismiss: () -> Unit,
    onDeleteAllClick: () -> Unit,
    onDeleteAllDismiss: () -> Unit,
    onDeleteAllConfirm: () -> Unit,
    onDeleteDismiss: () -> Unit,
    onDeleteConfirm: () -> Unit,
    onRenameValueChange: (String) -> Unit,
    onRenameDismiss: () -> Unit,
    onRenameConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {

    ShoppingListsScaffold(
        modifier = modifier,
        title = stringResource(Res.string.screen_title),
        action1 = TopBarAction("Search") {},
        action2 = TopBarAction("Delete",
            onClick = onDeleteAllClick),
        action3 = TopBarAction("Theme") {},
        onFabClick = onFabClick
    ) { padding ->

        if (visibleLists.isEmpty()) {
            ShoppingListsEmptyState(
                modifier = Modifier.padding(padding)
            )
        } else {
            ShoppingListsContent(
                lists = visibleLists,
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
            selectedIcon = state.lists
                .find { it.id == state.selectedListId }
                ?.icon,
            onIconSelect = onIconSelect,
            onDismiss = onSheetDismiss
        )
    }

    if (state.isDeleteAllDialogVisible) {
        DeleteAllListsDialog(
            onDismiss = onDeleteAllDismiss,
            onConfirm = onDeleteAllConfirm
        )
    }

    val selectedList = state.lists.find {
        it.id == state.selectedListId
    }

    if (state.isDeleteDialogVisible) {
        DeleteListDialog(
            listName = selectedList?.name.orEmpty(),
            onDismiss = onDeleteDismiss,
            onConfirm = onDeleteConfirm
        )
    }

    if (state.isRenameDialogVisible) {
        RenameListDialog(
            newName = state.renameValue,
            onRenameChange = onRenameValueChange,
            onDismiss = onRenameDismiss,
            onConfirm = onRenameConfirm
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
            visibleLists = state.lists,
            onFabClick = {},
            onEvent = {},
            onNameChange = {},
            onDismiss = {},
            onConfirm = {},
            onIconSelect = {},
            onSheetDismiss = {},
            onDeleteAllClick = {},
            onDeleteAllDismiss = {},
            onDeleteAllConfirm = {},
            onDeleteConfirm = {},
            onDeleteDismiss = {},
            onRenameValueChange = {},
            onRenameDismiss = {},
            onRenameConfirm = {}
        )
    }
}
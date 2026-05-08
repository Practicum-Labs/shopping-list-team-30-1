package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListsScaffold
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarAction
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsViewModel
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.bottom_sheet.IconPickerBottomSheet
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.CreateListDialog
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.screen_title

@Composable
fun ShoppingListsScreen(
    modifier: Modifier = Modifier,
    viewModel: ShoppingListsViewModel = viewModel() // TODO после реализации навигации вынести туда?
) {
    val state = viewModel.state

    ShoppingListsScaffold(
        modifier = modifier,
        title = stringResource(Res.string.screen_title),

        action1 = TopBarAction("Search") {},
        action2 = TopBarAction("Delete") {},
        action3 = TopBarAction("Theme") {},

        onFabClick = viewModel::onFabClick
    ) { padding ->

        if (state.lists.isEmpty()) {
            ShoppingListsEmptyState(
                modifier = Modifier.padding(padding)
            )
        } else {
            ShoppingListsContent(
                lists = state.lists,
                onEvent = viewModel::onCardEvent,
                modifier = Modifier.padding(padding)
            )
        }
    }

    if (state.isDialogVisible) {
        CreateListDialog(
            name = state.newListName,
            onNameChange = viewModel::onNameChange,
            onDismiss = viewModel::onDialogDismiss,
            onConfirm = viewModel::onCreateList
        )
    }

    if (state.isIconSheetVisible) {
        IconPickerBottomSheet(
            selectedIcon = state.lists
                .find { it.id == state.selectedListId }
                ?.icon,
            onIconSelected = viewModel::onIconSelected,
            onDismiss = viewModel::onSheetDismiss
        )
    }
}
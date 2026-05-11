package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsViewModel

@Composable
fun ShoppingListsScreen(
    modifier: Modifier = Modifier,
    viewModel: ShoppingListsViewModel
) {
    val state = viewModel.state
    val visibleLists = viewModel.visibleLists

    ShoppingListsScreenContent(
        state = state,
        visibleLists = visibleLists,
        onFabClick = viewModel::onFabClick,
        onEvent = viewModel::onCardEvent,
        onNameChange = viewModel::onNameChange,

        onDismiss = viewModel::onDialogDismiss,

        onConfirm = viewModel::onCreateList,
        onIconSelect = viewModel::onIconSelected,
        onSheetDismiss = viewModel::onSheetDismiss,

        onDeleteAllClick = viewModel::onDeleteAllClick,
        onDeleteAllConfirm = viewModel::onDeleteAllConfirm,

        onDeleteConfirm = viewModel::onDeleteConfirm,

        onRenameValueChange = viewModel::onRenameValueChange,
        onRenameConfirm = viewModel::onRenameConfirm,

        onSearchClick = viewModel::onSearchClick,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onSearchDismiss = viewModel::onSearchDismiss,

        modifier = modifier
    )
}
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
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListsScaffoldSearch
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarAction
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListCardEvent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListsState
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.bottom_sheet.IconPickerBottomSheet
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.CreateListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteAllListsDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.RenameListDialog
import io.dimasla4ee.shoppinglist.feature.welcome_screen.ui.WelcomeScreenLandscape
import io.dimasla4ee.shoppinglist.feature.welcome_screen.ui.WelcomeScreenPortrait
import io.dimasla4ee.shoppinglist.utils.OrientationProvider
import io.dimasla4ee.shoppinglist.utils.ScreenOrientation
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.screen_title

@Composable
fun ShoppingListsScreen(
    state: ShoppingListsState,
    visibleLists: List<ShoppingList>,

    onFabClick: (() -> Unit)?,
    onEvent: (ShoppingListCardEvent) -> Unit,

    onNameChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,

    onIconSelect: (ShoppingListIcon) -> Unit,
    onSheetDismiss: () -> Unit,

    onDeleteAllClick: () -> Unit,
    onDeleteAllConfirm: () -> Unit,

    onDeleteConfirm: () -> Unit,

    onRenameValueChange: (String) -> Unit,
    onRenameConfirm: () -> Unit,

    onThemeToggle: () -> Unit,

    onSearchClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchDismiss: () -> Unit,

    isDarkTheme: Boolean,

    modifier: Modifier = Modifier
) {
    if (state.isSearchMode) {
        ShoppingListsScaffoldSearch(
            query = state.searchQuery,
            onQueryChange = onSearchQueryChange,
            onBack = onSearchDismiss,
            onClear = { onSearchQueryChange("") }
        ) { padding ->

            when {
                visibleLists.isEmpty() &&
                        state.searchQuery.isNotEmpty() -> {

                    ShoppingListsSearchEmptyState(
                        modifier = Modifier.padding(padding)
                    )
                }

                visibleLists.isEmpty() -> {

                    ShoppingListsEmptyPortrait(
                        modifier = Modifier.padding(padding)
                    )
                }

                else -> {

                    ShoppingListsContent(
                        lists = visibleLists,
                        onEvent = onEvent,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }


    } else {

        ShoppingListsScaffold(
            modifier = modifier,
            title = stringResource(Res.string.screen_title),
            action1 = TopBarAction(
                "Search",
                onClick = onSearchClick
            ),
            action2 = TopBarAction(
                "Delete",
                onClick = onDeleteAllClick
            ),
            action3 = TopBarAction(
                "Theme",
                onClick = onThemeToggle
            ),

            isDarkTheme = isDarkTheme,
            onFabClick = if (state.isFabVisible) onFabClick else null
        ) { padding ->

            if (visibleLists.isEmpty()) {
                OrientationProvider { orientation ->
                    when (orientation) {
                        ScreenOrientation.PORTRAIT -> ShoppingListsEmptyPortrait(
                            modifier = Modifier.padding(padding)
                        )

                        ScreenOrientation.LANDSCAPE -> ShoppingListsEmptyLandscape(
                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            } else {
                ShoppingListsContent(
                    lists = visibleLists,
                    onEvent = onEvent,
                    modifier = Modifier.padding(padding)
                )
            }
        }
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

    when (val dialog = state.dialog) {

        ShoppingListDialog.None -> Unit

        ShoppingListDialog.Create -> {
            CreateListDialog(
                name = state.newListName,
                onNameChange = onNameChange,
                onDismiss = onDismiss,
                onConfirm = onConfirm
            )
        }

        is ShoppingListDialog.Rename -> {
            RenameListDialog(
                newName = state.renameValue,
                onRenameChange = onRenameValueChange,
                onDismiss = onDismiss,
                onConfirm = onRenameConfirm
            )
        }

        is ShoppingListDialog.Delete -> {
            val selectedList = state.lists.find {
                it.id == dialog.id
            }

            DeleteListDialog(
                listName = selectedList?.name.orEmpty(),
                onDismiss = onDismiss,
                onConfirm = onDeleteConfirm
            )
        }

        ShoppingListDialog.DeleteAll -> {
            DeleteAllListsDialog(
                onDismiss = onDismiss,
                onConfirm = onDeleteAllConfirm
            )
        }
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
        ShoppingListsScreen(
            state = state,
            visibleLists = when {
                state.searchQuery.isBlank() -> state.lists

                else -> state.lists.filter {
                    it.name.contains(state.searchQuery, ignoreCase = true)
                }
            },

            onFabClick = {},
            onEvent = {},
            onNameChange = {},

            onDismiss = {},
            onConfirm = {},

            onIconSelect = {},
            onSheetDismiss = {},

            onDeleteAllClick = {},
            onDeleteAllConfirm = {},

            onDeleteConfirm = {},

            onRenameValueChange = {},
            onRenameConfirm = {},

            onThemeToggle = {},

            onSearchClick = {},
            onSearchQueryChange = {},
            onSearchDismiss = {},

            isDarkTheme = false
        )
    }
}
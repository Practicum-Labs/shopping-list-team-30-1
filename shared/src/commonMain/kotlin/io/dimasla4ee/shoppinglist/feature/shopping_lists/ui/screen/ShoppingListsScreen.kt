package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListCardEvent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsIntent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsIntent.NameChanged
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsIntent.RenameValueChanged
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListsState
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.bottom_sheet.IconPickerBottomSheet
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.CreateListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteAllListsDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.LogoutDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.RenameListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold.ShoppingListsScaffold
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold.ShoppingListsScaffoldSearch
import io.dimasla4ee.shoppinglist.utils.OrientationProvider
import io.dimasla4ee.shoppinglist.utils.ScreenOrientation
import org.jetbrains.compose.resources.stringResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.action_delete_all
import shoppinglist.shared.generated.resources.action_login
import shoppinglist.shared.generated.resources.action_logout
import shoppinglist.shared.generated.resources.action_search
import shoppinglist.shared.generated.resources.action_theme
import shoppinglist.shared.generated.resources.fab_menu_create_shopping_list
import shoppinglist.shared.generated.resources.ic_delete_24
import shoppinglist.shared.generated.resources.ic_list_alt_add_24
import shoppinglist.shared.generated.resources.ic_login_24
import shoppinglist.shared.generated.resources.ic_logout_24
import shoppinglist.shared.generated.resources.ic_search_24
import shoppinglist.shared.generated.resources.ic_system_theme_24
import shoppinglist.shared.generated.resources.ic_theme_24
import shoppinglist.shared.generated.resources.ic_theme_light_24
import shoppinglist.shared.generated.resources.screen_title

@Composable
fun ShoppingListsScreen(
    state: ShoppingListsState,
    isAuthorized: Boolean,
    onIntent: (ShoppingListsIntent) -> Unit,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val visibleLists = remember(state.lists, state.searchQuery) {
        val query = state.searchQuery.trim()

        state.lists.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    OrientationProvider { orientation ->
        when (state.isSearchMode) {
            true -> SearchModeContent(
                state = state,
                visibleLists = visibleLists,
                orientation = orientation,
                onIntent = onIntent
            )

            false -> DefaultModeContent(
                state = state,
                visibleLists = visibleLists,
                orientation = orientation,
                isAuthorized = isAuthorized,
                onIntent = onIntent,
                onThemeToggle = onThemeToggle,
                modifier = modifier
            )
        }

        ShoppingListsDialogs(
            state = state,
            onIntent = onIntent
        )
    }
}

@Composable
private fun SearchModeContent(
    state: ShoppingListsState,
    visibleLists: List<ShoppingList>,
    orientation: ScreenOrientation,
    onIntent: (ShoppingListsIntent) -> Unit
) {
    ShoppingListsScaffoldSearch(
        query = state.searchQuery,
        onQueryChange = { onIntent(ShoppingListsIntent.SearchQueryChanged(it)) },
        onBack = { onIntent(ShoppingListsIntent.SearchDismiss) },
        onClear = { onIntent(ShoppingListsIntent.SearchQueryChanged("")) }
    ) { padding ->
        ShoppingListsBody(
            visibleLists = visibleLists,
            searchQuery = state.searchQuery,
            orientation = orientation,
            onIntent = onIntent,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
private fun DefaultModeContent(
    state: ShoppingListsState,
    visibleLists: List<ShoppingList>,
    orientation: ScreenOrientation,
    isAuthorized: Boolean,
    onIntent: (ShoppingListsIntent) -> Unit,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    ShoppingListsScaffold(
        modifier = modifier,
        title = stringResource(Res.string.screen_title),
        searchAction = ActionItem(
            iconRes = Res.drawable.ic_search_24,
            label = stringResource(Res.string.action_search),
            onClick = { onIntent(ShoppingListsIntent.SearchClicked) }
        ),
        deleteAllAction = ActionItem(
            iconRes = Res.drawable.ic_delete_24,
            label = stringResource(Res.string.action_delete_all),
            onClick = { onIntent(ShoppingListsIntent.DeleteAllClick) }
        ),
        themeSwitchAction = ActionItem(
            iconRes = when (LocalThemeMode.current) {
                ThemeMode.SYSTEM -> Res.drawable.ic_system_theme_24
                ThemeMode.LIGHT -> Res.drawable.ic_theme_24
                ThemeMode.DARK -> Res.drawable.ic_theme_light_24
            },
            label = stringResource(Res.string.action_theme),
            onClick = onThemeToggle
        ),
        authAction = ActionItem(
            iconRes = when (isAuthorized) {
                true -> Res.drawable.ic_logout_24
                false -> Res.drawable.ic_login_24
            },
            label = when (isAuthorized) {
                true -> Res.string.action_logout
                false -> Res.string.action_login
            }.let { res -> stringResource(res) },
            onClick = { onIntent(ShoppingListsIntent.AuthorizationClicked(isAuthorized)) }
        ),
        createListAction = ActionItem(
            iconRes = Res.drawable.ic_list_alt_add_24,
            label = stringResource(Res.string.fab_menu_create_shopping_list),
            onClick = { onIntent(ShoppingListsIntent.CreateListClicked) }
        ),
        hasShoppingLists = state.lists.isNotEmpty(),
        isLoading = state.isLoading
    ) { padding ->
        ShoppingListsBody(
            visibleLists = visibleLists,
            searchQuery = "",
            orientation = orientation,
            onIntent = onIntent,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
private fun ShoppingListsBody(
    visibleLists: List<ShoppingList>,
    searchQuery: String,
    orientation: ScreenOrientation,
    onIntent: (ShoppingListsIntent) -> Unit,
    modifier: Modifier = Modifier
) = when {
    visibleLists.isEmpty() && searchQuery.isNotEmpty() -> SearchEmptyContent(
        orientation = orientation,
        modifier = modifier
    )

    visibleLists.isEmpty() -> EmptyContent(
        orientation = orientation,
        modifier = modifier
    )

    else -> ShoppingListsContent(
        lists = visibleLists,
        onEvent = { event -> onIntent(event.toIntent()) },
        modifier = modifier
    )
}

@Composable
private fun EmptyContent(
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = when (orientation) {
    ScreenOrientation.PORTRAIT -> ShoppingListsEmptyPortrait(modifier)
    ScreenOrientation.LANDSCAPE -> ShoppingListsEmptyLandscape(modifier)
}

@Composable
private fun SearchEmptyContent(
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = when (orientation) {
    ScreenOrientation.PORTRAIT -> ShoppingListsSearchEmptyPortrait(modifier)
    ScreenOrientation.LANDSCAPE -> ShoppingListsSearchEmptyLandscape(modifier)
}

@Composable
private fun ShoppingListsDialogs(
    state: ShoppingListsState,
    onIntent: (ShoppingListsIntent) -> Unit
) {
    if (state.isIconSheetVisible) {
        IconPickerBottomSheet(
            selectedIcon = state.lists
                .find { it.id == state.selectedListId }
                ?.icon,
            onIconSelect = { onIntent(ShoppingListsIntent.IconSelected(it)) },
            onDismiss = { onIntent(ShoppingListsIntent.SheetDismiss) }
        )
    }

    when (val dialog = state.dialog) {
        ShoppingListDialog.None -> Unit

        ShoppingListDialog.Create -> {
            CreateListDialog(
                name = state.newListName,
                onNameChange = { onIntent(NameChanged(it)) },
                onDismiss = { onIntent(ShoppingListsIntent.DialogDismiss) },
                onConfirm = { onIntent(ShoppingListsIntent.CreateList) }
            )
        }

        is ShoppingListDialog.Rename -> {
            RenameListDialog(
                newName = state.renameValue,
                onRenameChange = { onIntent(RenameValueChanged(it)) },
                onDismiss = { onIntent(ShoppingListsIntent.DialogDismiss) },
                onConfirm = { onIntent(ShoppingListsIntent.RenameConfirm) }
            )
        }

        is ShoppingListDialog.Delete -> {
            DeleteListDialog(
                listName = dialog.name,
                onDismiss = { onIntent(ShoppingListsIntent.DialogDismiss) },
                onConfirm = { onIntent(ShoppingListsIntent.DeleteConfirm) }
            )
        }

        ShoppingListDialog.DeleteAll -> {
            DeleteAllListsDialog(
                onDismiss = { onIntent(ShoppingListsIntent.DialogDismiss) },
                onConfirm = { onIntent(ShoppingListsIntent.DeleteAllConfirm) }
            )
        }

        ShoppingListDialog.Logout -> {
            LogoutDialog(
                onDismiss = { onIntent(ShoppingListsIntent.DialogDismiss) },
                onConfirm = { onIntent(ShoppingListsIntent.LogoutClicked) }
            )
        }
    }
}

private fun ShoppingListCardEvent.toIntent(): ShoppingListsIntent = when (this) {
    is ShoppingListCardEvent.Click -> ShoppingListsIntent.ListClicked(item)
    is ShoppingListCardEvent.Edit -> ShoppingListsIntent.EditClicked(item)
    is ShoppingListCardEvent.Copy -> ShoppingListsIntent.CopyClicked(item)
    is ShoppingListCardEvent.ChangeIcon -> ShoppingListsIntent.ChangeIconClicked(item)
    is ShoppingListCardEvent.Delete -> ShoppingListsIntent.DeleteClicked(item)
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
            isAuthorized = true,
            onIntent = {},
            onThemeToggle = {}
        )
    }
}
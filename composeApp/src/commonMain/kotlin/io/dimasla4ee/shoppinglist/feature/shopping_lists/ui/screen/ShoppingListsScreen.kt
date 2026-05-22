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
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
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
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.action_delete_all
import shoppinglist.composeapp.generated.resources.action_login
import shoppinglist.composeapp.generated.resources.action_logout
import shoppinglist.composeapp.generated.resources.action_search
import shoppinglist.composeapp.generated.resources.action_theme
import shoppinglist.composeapp.generated.resources.ic_delete_list_24
import shoppinglist.composeapp.generated.resources.ic_login_24
import shoppinglist.composeapp.generated.resources.ic_logout_24
import shoppinglist.composeapp.generated.resources.ic_search_24
import shoppinglist.composeapp.generated.resources.ic_system_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_light_24
import shoppinglist.composeapp.generated.resources.screen_title

@Composable
fun ShoppingListsScreen(
    state: ShoppingListsState,
    isAuthorized: Boolean,
    onIntent: (ShoppingListsIntent) -> Unit,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val visibleLists = remember(
        state.lists,
        state.searchQuery
    ) {
        val query = state.searchQuery.trim()
        state.lists.filter { it.name.contains(query, ignoreCase = true) }
    }

    OrientationProvider { orientation ->
        if (state.isSearchMode) {
            ShoppingListsScaffoldSearch(
                query = state.searchQuery,
                onQueryChange = { onIntent(ShoppingListsIntent.SearchQueryChanged(it)) },
                onBack = { onIntent(ShoppingListsIntent.SearchDismiss) },
                onClear = { onIntent(ShoppingListsIntent.SearchQueryChanged("")) }
            ) { padding ->
                when {
                    visibleLists.isEmpty() && state.searchQuery.isNotEmpty() -> {
                        when (orientation) {
                            ScreenOrientation.PORTRAIT -> ShoppingListsSearchEmptyPortrait(
                                modifier = Modifier.padding(padding)
                            )

                            ScreenOrientation.LANDSCAPE -> ShoppingListsSearchEmptyLandscape(
                                modifier = Modifier.padding(padding)
                            )
                        }
                    }

                    visibleLists.isEmpty() -> {
                        when (orientation) {
                            ScreenOrientation.PORTRAIT -> ShoppingListsEmptyPortrait(
                                modifier = Modifier.padding(padding)
                            )

                            ScreenOrientation.LANDSCAPE -> ShoppingListsEmptyLandscape(
                                modifier = Modifier.padding(padding)
                            )
                        }
                    }

                    else -> {
                        ShoppingListsContent(
                            lists = visibleLists,
                            onEvent = { event ->
                                val intent = when (event) {
                                    is ShoppingListCardEvent.Click ->
                                        ShoppingListsIntent.ListClicked(event.item)

                                    is ShoppingListCardEvent.Edit ->
                                        ShoppingListsIntent.EditClicked(event.item)

                                    is ShoppingListCardEvent.Copy ->
                                        ShoppingListsIntent.CopyClicked(event.item)

                                    is ShoppingListCardEvent.ChangeIcon ->
                                        ShoppingListsIntent.ChangeIconClicked(event.item)

                                    is ShoppingListCardEvent.Delete ->
                                        ShoppingListsIntent.DeleteClicked(event.item)

                                }
                                onIntent(intent)
                            },

                            modifier = Modifier.padding(padding)
                        )
                    }
                }
            }

        } else {

            ShoppingListsScaffold(
                modifier = modifier,
                title = stringResource(Res.string.screen_title),
                onSearchClick = ActionItem(
                    iconRes = Res.drawable.ic_search_24,
                    label = stringResource(Res.string.action_search),
                    onClick = { onIntent(ShoppingListsIntent.SearchClick) }
                ),
                onDeleteAllClick = ActionItem(
                    iconRes = Res.drawable.ic_delete_list_24,
                    label = stringResource(Res.string.action_delete_all),
                    onClick = { onIntent(ShoppingListsIntent.DeleteAllClick) }
                ),
                onThemeSwitch = ActionItem(
                    iconRes = when (LocalThemeMode.current) {
                        ThemeMode.SYSTEM -> Res.drawable.ic_system_theme_24
                        ThemeMode.LIGHT -> Res.drawable.ic_theme_24
                        ThemeMode.DARK -> Res.drawable.ic_theme_light_24
                    },
                    label = stringResource(Res.string.action_theme),
                    onClick = onThemeToggle
                ),
                onAuthorizationClick = ActionItem(
                    iconRes = when (isAuthorized) {
                        true -> Res.drawable.ic_logout_24
                        false -> Res.drawable.ic_login_24
                    },
                    label = stringResource(
                        if (isAuthorized) Res.string.action_logout else Res.string.action_login
                    ),
                    onClick = { onIntent(ShoppingListsIntent.AuthorizationClicked(isAuthorized)) }
                ),
                onAddListClick = if (state.isFabVisible) {
                    { onIntent(ShoppingListsIntent.FabClick) }
                } else {
                    null
                }
            ) { padding ->

                if (visibleLists.isEmpty()) {
                    when (orientation) {
                        ScreenOrientation.PORTRAIT -> ShoppingListsEmptyPortrait(
                            modifier = Modifier.padding(padding)
                        )

                        ScreenOrientation.LANDSCAPE -> ShoppingListsEmptyLandscape(
                            modifier = Modifier.padding(padding)
                        )
                    }
                } else {
                    ShoppingListsContent(
                        lists = visibleLists,
                        onEvent = { event ->
                            val intent = when (event) {
                                is ShoppingListCardEvent.Click ->
                                    ShoppingListsIntent.ListClicked(event.item)

                                is ShoppingListCardEvent.Edit ->
                                    ShoppingListsIntent.EditClicked(event.item)

                                is ShoppingListCardEvent.Copy ->
                                    ShoppingListsIntent.CopyClicked(event.item)

                                is ShoppingListCardEvent.ChangeIcon ->
                                    ShoppingListsIntent.ChangeIconClicked(event.item)

                                is ShoppingListCardEvent.Delete ->
                                    ShoppingListsIntent.DeleteClicked(event.item)
                            }
                            onIntent(intent)
                        },

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
                onIconSelect = { icon: ShoppingListIcon ->
                    onIntent(ShoppingListsIntent.IconSelected(icon))
                },
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
                    onConfirm = { onIntent(ShoppingListsIntent.LogoutClick) })
            }
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
            isAuthorized = true,
            onIntent = {},
            onThemeToggle = {}
        )
    }
}
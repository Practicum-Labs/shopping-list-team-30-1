package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListsScaffold
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListsScaffoldSearch
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarAction
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListCardEvent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsIntent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListsState
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.bottom_sheet.IconPickerBottomSheet
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.CreateListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteAllListsDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.DeleteListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.dialog.RenameListDialog
import io.dimasla4ee.shoppinglist.utils.OrientationProvider
import io.dimasla4ee.shoppinglist.utils.ScreenOrientation
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.screen_title

@Composable
fun ShoppingListsScreen(
    state: ShoppingListsState,
    onIntent: (ShoppingListsIntent) -> Unit,
    onThemeToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val visibleLists = remember(
        state.lists,
        state.searchQuery
    ) {
        val query = state.searchQuery.trim()

        if (query.isEmpty()) {
            state.lists
        } else {
            state.lists.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

    OrientationProvider { orientation ->
        if (state.isSearchMode) {
            ShoppingListsScaffoldSearch(
                query = state.searchQuery,
                onQueryChange = {
                    onIntent(
                        ShoppingListsIntent.SearchQueryChanged(it)
                    )
                },
                onBack = {
                    onIntent(
                        ShoppingListsIntent.SearchDismiss
                    )
                },
                onClear = {
                    onIntent(
                        ShoppingListsIntent.SearchQueryChanged("")
                    )
                }
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
                                when (event) {
                                    is ShoppingListCardEvent.Click -> {
                                        onIntent(
                                            ShoppingListsIntent.ListClicked(
                                                event.item
                                            )
                                        )
                                    }

                                    is ShoppingListCardEvent.Edit -> {
                                        onIntent(
                                            ShoppingListsIntent.EditClicked(
                                                event.item
                                            )
                                        )
                                    }

                                    is ShoppingListCardEvent.Copy -> {
                                        onIntent(
                                            ShoppingListsIntent.CopyClicked(
                                                event.item
                                            )
                                        )
                                    }

                                    is ShoppingListCardEvent.ChangeIcon -> {
                                        onIntent(
                                            ShoppingListsIntent.ChangeIconClicked(
                                                event.item
                                            )
                                        )
                                    }

                                    is ShoppingListCardEvent.Delete -> {
                                        onIntent(
                                            ShoppingListsIntent.DeleteClicked(
                                                event.item
                                            )
                                        )
                                    }
                                }
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
                action1 = TopBarAction(
                    "Search",
                    onClick = {
                        onIntent(
                            ShoppingListsIntent.SearchClick
                        )
                    }
                ),
                action2 = TopBarAction(
                    "Delete",
                    onClick = {
                        onIntent(
                            ShoppingListsIntent.DeleteAllClick
                        )
                    }
                ),
                action3 = TopBarAction(
                    "Theme",
                    onClick = onThemeToggle
                ),

                onFabClick = if (state.isFabVisible) {
                    {
                        onIntent(
                            ShoppingListsIntent.FabClick
                        )
                    }
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

                            when (event) {

                                is ShoppingListCardEvent.Click -> {
                                    onIntent(
                                        ShoppingListsIntent.ListClicked(
                                            event.item
                                        )
                                    )
                                }

                                is ShoppingListCardEvent.Edit -> {
                                    onIntent(
                                        ShoppingListsIntent.EditClicked(
                                            event.item
                                        )
                                    )
                                }

                                is ShoppingListCardEvent.Copy -> {
                                    onIntent(
                                        ShoppingListsIntent.CopyClicked(
                                            event.item
                                        )
                                    )
                                }

                                is ShoppingListCardEvent.ChangeIcon -> {
                                    onIntent(
                                        ShoppingListsIntent.ChangeIconClicked(
                                            event.item
                                        )
                                    )
                                }

                                is ShoppingListCardEvent.Delete -> {
                                    onIntent(
                                        ShoppingListsIntent.DeleteClicked(
                                            event.item
                                        )
                                    )
                                }
                            }
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

                    onIntent(
                        ShoppingListsIntent.IconSelected(icon)
                    )
                },
                onDismiss = {
                    onIntent(
                        ShoppingListsIntent.SheetDismiss
                    )
                }
            )
        }

        when (val dialog = state.dialog) {

            ShoppingListDialog.None -> Unit

            ShoppingListDialog.Create -> {
                CreateListDialog(
                    name = state.newListName,
                    onNameChange = {
                        onIntent(
                            ShoppingListsIntent.NameChanged(it)
                        )
                    },

                    onDismiss = {
                        onIntent(
                            ShoppingListsIntent.DialogDismiss
                        )
                    },
                    onConfirm = {
                        onIntent(
                            ShoppingListsIntent.CreateList
                        )
                    }
                )
            }

            is ShoppingListDialog.Rename -> {
                RenameListDialog(
                    newName = state.renameValue,
                    onRenameChange = {
                        onIntent(
                            ShoppingListsIntent.RenameValueChanged(it)
                        )
                    },
                    onDismiss = {
                        onIntent(
                            ShoppingListsIntent.DialogDismiss
                        )
                    },
                    onConfirm = {
                        onIntent(
                            ShoppingListsIntent.RenameConfirm
                        )
                    }
                )
            }

            is ShoppingListDialog.Delete -> {

                DeleteListDialog(
                    listName = dialog.name,
                    onDismiss = {
                        onIntent(
                            ShoppingListsIntent.DialogDismiss
                        )
                    },
                    onConfirm = {
                        onIntent(
                            ShoppingListsIntent.DeleteConfirm
                        )
                    }
                )
            }

            ShoppingListDialog.DeleteAll -> {
                DeleteAllListsDialog(
                    onDismiss = {
                        onIntent(
                            ShoppingListsIntent.DialogDismiss
                        )
                    },
                    onConfirm = {
                        onIntent(
                            ShoppingListsIntent.DeleteAllConfirm
                        )
                    }
                )
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
            onIntent = {},
            onThemeToggle = {}
        )
    }
}
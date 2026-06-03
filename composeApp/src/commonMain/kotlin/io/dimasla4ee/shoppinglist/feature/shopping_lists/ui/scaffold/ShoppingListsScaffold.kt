package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.components.AppFloatingActionButton
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FabMenuFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.DropdownMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FigmaTopBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.fab_menu_create_shopping_list
import shoppinglist.composeapp.generated.resources.ic_fab_24
import shoppinglist.composeapp.generated.resources.ic_list_alt_add_24

@Composable
fun ShoppingListsScaffold(
    title: String,
    hasShoppingLists: Boolean,
    onSearchClick: ActionItem,
    onDeleteAllAction: ActionItem,
    onThemeSwitch: ActionItem,
    onAuthorizationClick: ActionItem,
    onAddListClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    screenVariant: ShoppingListsScreenVariant = ShoppingListsScreenVariant.FAB_MENU,
    content: @Composable (PaddingValues) -> Unit
) {
    var isFabMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            when (screenVariant) {
                ShoppingListsScreenVariant.FIGMA -> FigmaTopBar(
                    title = title,
                    onSearchClick = onSearchClick,
                    onDeleteAllClick = onDeleteAllAction,
                    onThemeSwitch = onThemeSwitch,
                    onAuthorizationClick = onAuthorizationClick
                )

                ShoppingListsScreenVariant.FAB_MENU -> FigmaTopBar(
                    title = title,
                    onSearchClick = onSearchClick,
                    onThemeSwitch = onThemeSwitch,
                    onAuthorizationClick = onAuthorizationClick
                )

                ShoppingListsScreenVariant.DROPDOWN_MENU -> DropdownMenuTopBar(
                    title = title,
                    onSearchClick = onSearchClick,
                    onDeleteAllClick = onDeleteAllAction,
                    onThemeSwitch = onThemeSwitch,
                    onAuthorizationClick = onAuthorizationClick,
                    themeMode = LocalThemeMode.current
                )
            }
        },
        floatingActionButton = {
            when (screenVariant) {
                ShoppingListsScreenVariant.FIGMA -> AppFloatingActionButton(
                    iconRes = painterResource(Res.drawable.ic_fab_24),
                    onClick = onAddListClick
                )

                ShoppingListsScreenVariant.FAB_MENU -> key(hasShoppingLists) {
                    FabMenuFloatingActionButton(
                        onDeleteAllAction = onDeleteAllAction,
                        onAddListAction = ActionItem(
                            iconRes = Res.drawable.ic_list_alt_add_24,
                            label = stringResource(Res.string.fab_menu_create_shopping_list),
                            onClick = { onAddListClick?.invoke() }
                        ),
                        hasShoppingLists = hasShoppingLists,
                        isMenuExpanded = isFabMenuExpanded,
                        onMenuExpand = { isExpanded -> isFabMenuExpanded = isExpanded },
                    )
                }

                ShoppingListsScreenVariant.DROPDOWN_MENU -> AppFloatingActionButton(
                    iconRes = painterResource(Res.drawable.ic_fab_24),
                    onClick = onAddListClick
                )
            }
        },
        content = content
    )
}
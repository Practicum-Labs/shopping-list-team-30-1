package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.components.AppFloatingActionButton
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FabMenuFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.DropdownMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FigmaTopBar
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_fab_24

@Composable
fun ShoppingListsScaffold(
    title: String,
    hasShoppingLists: Boolean,
    onSearchClick: ActionItem,
    onDeleteAllClick: ActionItem,
    onThemeSwitch: ActionItem,
    onAuthorizationClick: ActionItem,
    onAddListClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    screenVariant: ShoppingListsScreenVariant = ShoppingListsScreenVariant.FAB_MENU,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            when (screenVariant) {
                ShoppingListsScreenVariant.FIGMA -> FigmaTopBar(
                    title = title,
                    onSearchClick = onSearchClick,
                    onDeleteAllClick = onDeleteAllClick,
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
                    onDeleteAllClick = onDeleteAllClick,
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
                        onDeleteAllClick = onDeleteAllClick,
                        onAddListClick = { onAddListClick?.invoke() },
                        hasShoppingLists = hasShoppingLists
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
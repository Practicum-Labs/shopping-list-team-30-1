package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FabMenuFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FigmaFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.DropdownMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FigmaTopBar

@Composable
fun ShoppingListsScaffold(
    title: String,
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
                ShoppingListsScreenVariant.FIGMA -> FigmaFloatingActionButton(
                    onAddListClick = onAddListClick
                )

                ShoppingListsScreenVariant.FAB_MENU -> FabMenuFloatingActionButton(
                    onDeleteAllClick = onDeleteAllClick,
                    onAddListClick = onAddListClick
                )

                ShoppingListsScreenVariant.DROPDOWN_MENU -> FigmaFloatingActionButton(
                    onAddListClick = onAddListClick
                )
            }
        },
        content = content
    )
}
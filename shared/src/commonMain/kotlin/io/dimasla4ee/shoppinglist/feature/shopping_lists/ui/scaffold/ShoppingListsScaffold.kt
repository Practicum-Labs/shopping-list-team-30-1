package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.components.AppFloatingActionButton
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab.FabMenuFloatingActionButton
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.DropdownMenuTopBar
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.topbar.FigmaTopBar
import org.jetbrains.compose.resources.painterResource
import shoppinglist.shared.generated.resources.Res
import shoppinglist.shared.generated.resources.ic_fab_24

@Composable
fun ShoppingListsScaffold(
    title: String,
    hasShoppingLists: Boolean,
    isLoading: Boolean,
    searchAction: ActionItem,
    deleteAllAction: ActionItem,
    themeSwitchAction: ActionItem,
    authAction: ActionItem,
    createListAction: ActionItem,
    modifier: Modifier = Modifier,
    screenVariant: ShoppingListsScreenVariant = ShoppingListsScreenVariant.FAB_MENU,
    content: @Composable (PaddingValues) -> Unit
) {
    var isFabMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            AnimatedVisibility(
                visible = !isLoading,
                enter = slideInVertically(initialOffsetY = { -it / 2 })
            ) {
                when (screenVariant) {
                    ShoppingListsScreenVariant.FIGMA -> FigmaTopBar(
                        title = title,
                        onSearchClick = searchAction,
                        onDeleteAllClick = deleteAllAction,
                        onThemeSwitch = themeSwitchAction,
                        onAuthorizationClick = authAction
                    )

                    ShoppingListsScreenVariant.FAB_MENU -> FigmaTopBar(
                        title = title,
                        onSearchClick = searchAction,
                        onThemeSwitch = themeSwitchAction,
                        onAuthorizationClick = authAction
                    )

                    ShoppingListsScreenVariant.DROPDOWN_MENU -> DropdownMenuTopBar(
                        title = title,
                        onSearchClick = searchAction,
                        onDeleteAllClick = deleteAllAction,
                        onThemeSwitch = themeSwitchAction,
                        onAuthorizationClick = authAction,
                        themeMode = LocalThemeMode.current
                    )
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = !isLoading,
                enter = slideInVertically(initialOffsetY = { it / 2 })
            ) {
                when (screenVariant) {
                    ShoppingListsScreenVariant.FIGMA -> AppFloatingActionButton(
                        iconRes = painterResource(Res.drawable.ic_fab_24),
                        onClick = createListAction.onClick
                    )

                    ShoppingListsScreenVariant.FAB_MENU -> key(hasShoppingLists) {
                        FabMenuFloatingActionButton(
                            modifier = Modifier.offset(y = 0.dp),
                            onDeleteAllAction = deleteAllAction,
                            onAddListAction = createListAction,
                            hasShoppingLists = hasShoppingLists,
                            isMenuExpanded = isFabMenuExpanded,
                            onMenuExpand = { isExpanded -> isFabMenuExpanded = isExpanded },
                        )
                    }

                    ShoppingListsScreenVariant.DROPDOWN_MENU -> AppFloatingActionButton(
                        iconRes = painterResource(Res.drawable.ic_fab_24),
                        onClick = createListAction.onClick
                    )
                }
            }
        },
        content = { paddingValues ->
            AnimatedVisibility(
                visible = !isLoading,
                enter = slideInHorizontally(initialOffsetX = { it / 2 }),
                content = { content(paddingValues) }
            )
        }
    )
}
package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import io.dimasla4ee.shoppinglist.core.presentation.components.fab_menu.AppFloatingActionButtonMenu
import io.dimasla4ee.shoppinglist.core.presentation.components.fab_menu.AppFloatingActionButtonMenuItem
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_toggle_menu
import shoppinglist.composeapp.generated.resources.fab_menu_create_shopping_list
import shoppinglist.composeapp.generated.resources.fab_menu_delete_all
import shoppinglist.composeapp.generated.resources.ic_add_56
import shoppinglist.composeapp.generated.resources.ic_close_24
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_list_alt_add_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24
import shoppinglist.composeapp.generated.resources.state_collapsed
import shoppinglist.composeapp.generated.resources.state_expanded

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FabMenuFloatingActionButton(
    hasShoppingLists: Boolean,
    isMenuExpanded: Boolean,
    onMenuExpand: () -> Unit,
    onDeleteAllClick: ActionItem,
    onAddListClick: (() -> Unit),
    modifier: Modifier = Modifier
) {
    val contentDesc = stringResource(Res.string.content_toggle_menu)
    val stateDesc = when (isMenuExpanded) {
        true -> Res.string.state_expanded
        false -> Res.string.state_collapsed
    }.let { stringResource(it) }

    fun collapseAnd(action: () -> Unit) {
        action()
        onMenuExpand()
    }

    FloatingActionButtonMenu(
        expanded = isMenuExpanded,
        button = {
            ToggleFloatingActionButton(
                modifier = modifier.semantics {
                    traversalIndex = -1f
                    stateDescription = stateDesc
                    contentDescription = contentDesc
                },
                checked = isMenuExpanded,
                onCheckedChange = {
                    when (hasShoppingLists) {
                        true -> onMenuExpand()
                        false -> onAddListClick()
                    }
                },
                containerColor = AppFloatingActionButtonMenu.containerColor()
            ) {
                Icon(
                    painter = painterResource(
                        fabIcon(hasShoppingLists, checkedProgress)
                    ),
                    contentDescription = null,
                    modifier = Modifier.animateIcon(
                        checkedProgress = { checkedProgress },
                        color = AppFloatingActionButtonMenu.iconColor()
                    )
                )
            }
        }
    ) {
        if (!hasShoppingLists) return@FloatingActionButtonMenu

        AppFloatingActionButtonMenuItem(
            onClick = { collapseAnd { onDeleteAllClick.onClick() } },
            painter = painterResource(Res.drawable.ic_delete_24),
            label = stringResource(Res.string.fab_menu_delete_all)
        )
        AppFloatingActionButtonMenuItem(
            onClick = { collapseAnd { onAddListClick() } },
            painter = painterResource(Res.drawable.ic_list_alt_add_24),
            label = stringResource(Res.string.fab_menu_create_shopping_list)
        )
    }
}

private fun fabIcon(
    hasShoppingLists: Boolean,
    checkedProgress: Float
): DrawableResource = when {
    !hasShoppingLists -> Res.drawable.ic_add_56
    checkedProgress > 0.5f -> Res.drawable.ic_close_24
    else -> Res.drawable.ic_shopping_cart_24
}
package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.core.presentation.components.fab_menu.AppFloatingActionButtonMenu
import io.dimasla4ee.shoppinglist.core.presentation.components.fab_menu.AppFloatingActionButtonMenuDefaults
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_toggle_menu
import shoppinglist.composeapp.generated.resources.ic_add_56
import shoppinglist.composeapp.generated.resources.ic_close_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24
import shoppinglist.composeapp.generated.resources.state_collapsed
import shoppinglist.composeapp.generated.resources.state_expanded

@Composable
fun FabMenuFloatingActionButton(
    hasShoppingLists: Boolean,
    isMenuExpanded: Boolean,
    onMenuExpand: (Boolean) -> Unit,
    onDeleteAllAction: ActionItem,
    onAddListAction: ActionItem,
    modifier: Modifier = Modifier
) {
    AppFloatingActionButtonMenu(
        modifier = modifier,
        isExpanded = isMenuExpanded,
        onExpand = onMenuExpand,
        description = AppFloatingActionButtonMenuDefaults.Description(
            content = stringResource(Res.string.content_toggle_menu),
            expanded = stringResource(Res.string.state_expanded),
            shrunken = stringResource(Res.string.state_collapsed)
        ),
        fabIconRes = { progress -> fabIcon(hasShoppingLists, progress) },
        fabOnClick = {
            when (hasShoppingLists) {
                true -> onMenuExpand(!isMenuExpanded)
                false -> onAddListAction.onClick()
            }
        },
        actions = listOf(
            onDeleteAllAction,
            onAddListAction
        )
    )
}

private fun fabIcon(
    hasShoppingLists: Boolean,
    checkedProgress: Float
): DrawableResource = when {
    !hasShoppingLists -> Res.drawable.ic_add_56
    checkedProgress > ICON_PROGRESS_THRESHOLD -> Res.drawable.ic_close_24
    else -> Res.drawable.ic_shopping_cart_24
}

private const val ICON_PROGRESS_THRESHOLD = 0.5f
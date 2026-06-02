package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.FloatingActionButtonMenuScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_toggle_menu
import shoppinglist.composeapp.generated.resources.fab_menu_create_shopping_list
import shoppinglist.composeapp.generated.resources.fab_menu_delete_all
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
    onDeleteAllClick: ActionItem,
    onAddListClick: (() -> Unit),
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(hasShoppingLists) {
        if (!hasShoppingLists) isExpanded = false
    }

    val contentDesc = stringResource(Res.string.content_toggle_menu)
    val stateDesc = when (isExpanded) {
        true -> Res.string.state_expanded
        false -> Res.string.state_collapsed
    }.let { stringResource(it) }

    fun collapseAnd(action: () -> Unit) {
        action()
        isExpanded = false
    }

    FloatingActionButtonMenu(
        expanded = isExpanded,
        button = {
            ToggleFloatingActionButton(
                modifier = modifier.semantics {
                    traversalIndex = -1f
                    stateDescription = stateDesc
                    contentDescription = contentDesc
                },
                checked = isExpanded,
                onCheckedChange = {
                    if (hasShoppingLists) {
                        isExpanded = !isExpanded
                    } else {
                        onAddListClick()
                    }
                },
                containerColor = ToggleFloatingActionButtonDefaults.containerColor(
                    initialColor = MaterialTheme.colorScheme.primary,
                    finalColor = MaterialTheme.colorScheme.primaryContainer,
                )
            ) {
                val iconRes = fabIcon(hasShoppingLists, checkedProgress)

                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    modifier = Modifier.animateIcon(
                        checkedProgress = { checkedProgress },
                        color = ToggleFloatingActionButtonDefaults.iconColor(
                            initialColor = MaterialTheme.colorScheme.onPrimary,
                            finalColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
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
    !hasShoppingLists -> Res.drawable.ic_list_alt_add_24
    checkedProgress > 0.5f -> Res.drawable.ic_close_24
    else -> Res.drawable.ic_shopping_cart_24
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FloatingActionButtonMenuScope.AppFloatingActionButtonMenuItem(
    onClick: () -> Unit,
    painter: Painter,
    label: String,
    modifier: Modifier = Modifier
) = FloatingActionButtonMenuItem(
    modifier = modifier,
    onClick = onClick,
    text = { Text(text = label, style = MaterialTheme.typography.labelMedium) },
    icon = { Icon(painter = painter, contentDescription = null) }
)
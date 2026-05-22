package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_toggle_menu
import shoppinglist.composeapp.generated.resources.ic_close_24
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_list_alt_add_24
import shoppinglist.composeapp.generated.resources.ic_shopping_cart_24
import shoppinglist.composeapp.generated.resources.state_collapsed
import shoppinglist.composeapp.generated.resources.state_expanded

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FabMenuFloatingActionButton(
    onDeleteAllClick: ActionItem,
    onAddListClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    val stateDesc = if (isExpanded) {
        stringResource(Res.string.state_expanded)
    } else {
        stringResource(Res.string.state_collapsed)
    }

    val contentDesc = stringResource(Res.string.content_toggle_menu)

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
                onCheckedChange = { isExpanded = !isExpanded },
                containerColor = ToggleFloatingActionButtonDefaults.containerColor(
                    initialColor = MaterialTheme.colorScheme.primary,
                    finalColor = MaterialTheme.colorScheme.primaryContainer,
                )
            ) {
                val iconRes by remember {
                    derivedStateOf {
                        if (checkedProgress > 0.5f)
                            Res.drawable.ic_close_24
                        else
                            Res.drawable.ic_shopping_cart_24
                    }
                }
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
        FloatingActionButtonMenuItem(
            onClick = {
                onDeleteAllClick.onClick()
                isExpanded = false
            },
            text = { Text("Delete all", style = MaterialTheme.typography.labelMedium) },
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_delete_24),
                    contentDescription = null
                )
            }
        )
        FloatingActionButtonMenuItem(
            onClick = {
                onAddListClick?.invoke()
                isExpanded = false
            },
            text = {
                Text(
                    "Create shopping list",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_list_alt_add_24),
                    contentDescription = null
                )
            }
        )
    }
}
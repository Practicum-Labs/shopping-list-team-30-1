package io.dimasla4ee.shoppinglist.core.presentation.components.fab_menu

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppFloatingActionButtonMenu(
    isExpanded: Boolean,
    onExpand: (isExpanded: Boolean) -> Unit,
    description: AppFloatingActionButtonMenuDefaults.Description,
    fabIconRes: (Float) -> DrawableResource,
    fabOnClick: () -> Unit,
    actions: List<ActionItem>,
    modifier: Modifier = Modifier,
    containerColor: (Float) -> Color = AppFloatingActionButtonMenuDefaults.containerColor(),
    iconColor: (Float) -> Color = AppFloatingActionButtonMenuDefaults.iconColor()
) {
    fun collapseAnd(action: () -> Unit) {
        action()
        onExpand(false)
    }

    FloatingActionButtonMenu(
        modifier = modifier,
        expanded = isExpanded,
        button = {
            ToggleFloatingActionButton(
                modifier = modifier.semantics {
                    traversalIndex = -1f
                    stateDescription = description.byState(isExpanded = isExpanded)
                    contentDescription = description.content
                },
                checked = isExpanded,
                onCheckedChange = { fabOnClick() },
                containerColor = containerColor
            ) {
                Icon(
                    painter = painterResource(fabIconRes(checkedProgress)),
                    contentDescription = null,
                    modifier = Modifier.animateIcon(
                        checkedProgress = { checkedProgress },
                        color = iconColor
                    )
                )
            }
        }
    ) {
        actions.forEach { action ->
            AppFloatingActionButtonMenuItem(
                onClick = { collapseAnd { action.onClick() } },
                painter = painterResource(action.iconRes!!),
                label = action.label ?: "PLACEHOLDER_TEXT"
            )
        }
    }
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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
object AppFloatingActionButtonMenuDefaults {

    @Composable
    fun iconColor(
        initialColor: Color = MaterialTheme.colorScheme.onPrimary,
        finalColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
    ): (Float) -> Color = ToggleFloatingActionButtonDefaults.iconColor(
        initialColor = initialColor,
        finalColor = finalColor
    )

    @Composable
    fun containerColor(
        initialColor: Color = MaterialTheme.colorScheme.primary,
        finalColor: Color = MaterialTheme.colorScheme.primaryContainer
    ) = ToggleFloatingActionButtonDefaults.containerColor(
        initialColor = initialColor,
        finalColor = finalColor
    )

    data class Description(
        val content: String,
        val expanded: String,
        val shrunken: String
    ) {
        fun byState(isExpanded: Boolean) = if (isExpanded) expanded else shrunken
    }

}
package io.dimasla4ee.shoppinglist.core.presentation.components.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24
import shoppinglist.composeapp.generated.resources.ic_cancel_circle_24
import shoppinglist.composeapp.generated.resources.ic_library_add_check_24
import shoppinglist.composeapp.generated.resources.ic_menu_24
import shoppinglist.composeapp.generated.resources.ic_remove_circle_24


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable (() -> Unit))? = null,
    actions: List<ActionItem> = emptyList(),
    colors: TopAppBarColors = AppTopBarDefaults.productsTopBarColors(),
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = contentColor
            )
        },
        colors = colors,
        navigationIcon = { navigationIcon?.invoke() },
        actions = {
            actions.forEach { action ->
                if (action.iconRes != null) {
                    IconButton(onClick = action.onClick) {
                        Icon(
                            painter = painterResource(action.iconRes),
                            contentDescription = action.label,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AppTopBarPreview() {
    ShoppingListTheme {
        AppTopBar(
            title = "Название списка",
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back_24),
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            },
            actions = listOf(
                ActionItem(
                    iconRes = Res.drawable.ic_menu_24,
                    label = "Menu",
                    onClick = {}
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IvanTopBar() {
    ShoppingListTheme {
        AppTopBar(
            title = "Мои списки",
            actions = listOf(
                ActionItem(Res.drawable.ic_library_add_check_24, "Search") {},
                ActionItem(Res.drawable.ic_remove_circle_24, "Sort") {},
                ActionItem(Res.drawable.ic_cancel_circle_24, "Menu") {}
            )
        )
    }
}

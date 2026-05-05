package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_arrow_back_24
import shoppinglist.composeapp.generated.resources.ic_cancel_circle_24
import shoppinglist.composeapp.generated.resources.ic_library_add_check_24
import shoppinglist.composeapp.generated.resources.ic_menu_24
import shoppinglist.composeapp.generated.resources.ic_remove_circle_24
import shoppinglist.composeapp.generated.resources.ic_trailing_16


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable (() -> Unit))? = null,
    actions: List<TopBarIcon> = emptyList()
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(title) },

        navigationIcon = {
            navigationIcon?.invoke()
        },

        actions = {
            actions.forEach { action ->
                IconButton(onClick = action.onClick) {
                    Icon(
                        painter = painterResource(action.icon),
                        contentDescription = action.contentDescription
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AppTopBarPreview() {
    MaterialTheme {
        AppTopBar(
            title = "Название списка",
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_arrow_back_24),
                        contentDescription = "Back"
                    )
                }
            },
            actions = listOf(
                TopBarIcon(
                    icon = Res.drawable.ic_menu_24,
                    contentDescription = "Menu",
                    onClick = {}
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IvanTopBar() {
    MaterialTheme {
        AppTopBar(
            title = "Мои списки",
            actions = listOf(
                TopBarIcon(Res.drawable.ic_library_add_check_24, "Search") {},
                TopBarIcon(Res.drawable.ic_remove_circle_24, "Sort") {},
                TopBarIcon(Res.drawable.ic_cancel_circle_24, "Menu") {}
            )
        )
    }
}

data class TopBarIcon(
    val icon: DrawableResource,
    val contentDescription: String? = null,
    val onClick: () -> Unit
)
package io.dimasla4ee.shoppinglist.app.presentantion.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.LocalThemeMode
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.app.ui.theme.ThemeMode
import io.dimasla4ee.shoppinglist.core.presentation.model.ActionItem
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.scaffold.ShoppingListsScaffold
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_delete_24
import shoppinglist.composeapp.generated.resources.ic_login_24
import shoppinglist.composeapp.generated.resources.ic_search_24
import shoppinglist.composeapp.generated.resources.ic_system_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_24
import shoppinglist.composeapp.generated.resources.ic_theme_light_24
import shoppinglist.composeapp.generated.resources.screen_title

@Composable
private fun PreviewContainer(
    content: @Composable (PaddingValues) -> Unit
) {
    ShoppingListTheme {
        ShoppingListsScaffold(
            title = stringResource(Res.string.screen_title),
            onSearchClick = ActionItem(
                iconRes = Res.drawable.ic_search_24,
                label = "Search",
                onClick = { }
            ),
            onDeleteAllAction = ActionItem(
                iconRes = Res.drawable.ic_delete_24,
                label = "Delete",
                onClick = { }
            ),
            onThemeSwitch = ActionItem(
                iconRes = when (LocalThemeMode.current) {
                    ThemeMode.SYSTEM -> Res.drawable.ic_system_theme_24
                    ThemeMode.LIGHT -> Res.drawable.ic_theme_24
                    ThemeMode.DARK -> Res.drawable.ic_theme_light_24
                },
                label = "Theme",
                onClick = {}
            ),

            onAddListClick = {},
            content = content,
            onAuthorizationClick = ActionItem(
                iconRes = Res.drawable.ic_login_24,
                label = null,
                onClick = { }
            ),
            hasShoppingLists = true
        )
    }
}

@Preview(name = "Empty - Light", showBackground = true)
@Preview(
    name = "Empty - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun AppScreen_Empty_Preview() {
    PreviewContainer { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text(text = "Список пуст")
        }
    }
}

@Preview(name = "List - Light", showBackground = true)
@Preview(
    name = "List - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun AppScreen_List_Preview() {
    val items = List(5) { "Item $it" }

    PreviewContainer { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(AppDimensions.paddingMedium)
                )
            }
        }
    }
}
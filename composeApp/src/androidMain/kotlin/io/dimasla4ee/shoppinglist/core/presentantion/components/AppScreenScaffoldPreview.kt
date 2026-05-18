package io.dimasla4ee.shoppinglist.core.presentantion.components

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
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.presentation.components.ShoppingListsScaffold
import io.dimasla4ee.shoppinglist.core.presentation.components.TopBarAction

@Composable
private fun PreviewContainer(
    content: @Composable (PaddingValues) -> Unit
) {
    ShoppingListTheme {
        ShoppingListsScaffold(
            title = "Мои списки",

            action1 = TopBarAction(
                contentDescription = "Search",
                onClick = {}
            ),
            action2 = TopBarAction(
                contentDescription = "Delete",
                onClick = {}
            ),
            action3 = TopBarAction(
                contentDescription = "Theme",
                onClick = {}
            ),

            onFabClick = {},

            content = content
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
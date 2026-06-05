package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListCardEvent
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.mappers.toUi
import io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.ShoppingListCard

@Composable
fun ShoppingListsContent(
    lists: List<ShoppingList>,
    onEvent: (ShoppingListCardEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(AppDimensions.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(AppDimensions.paddingSmall)
    ) {
        items(
            items = lists,
            key = { it.id }
        ) { list ->

            ShoppingListCard(
                listItem = list.toUi(),
                onEvent = onEvent
            )
        }
    }
}
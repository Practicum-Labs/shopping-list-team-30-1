package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.mappers

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.presentation.mappers.toDrawableResource
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListItem

fun ShoppingList.toUi(): ShoppingListItem {
    return ShoppingListItem(
        id = id,
        name = name,
        iconRes = icon.toDrawableResource()
    )
}
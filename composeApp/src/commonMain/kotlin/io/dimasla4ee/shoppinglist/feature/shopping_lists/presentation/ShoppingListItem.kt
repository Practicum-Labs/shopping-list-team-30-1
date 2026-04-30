package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import org.jetbrains.compose.resources.DrawableResource

data class ShoppingListItem(
    val id: Long,
    val name: String,
    val iconRes: DrawableResource
)
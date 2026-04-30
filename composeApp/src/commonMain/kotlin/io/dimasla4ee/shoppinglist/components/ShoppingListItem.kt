package io.dimasla4ee.shoppinglist.components

import org.jetbrains.compose.resources.DrawableResource

data class ShoppingListItem(
    val id: Long,
    val name: String,
    val iconRes: DrawableResource
)
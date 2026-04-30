package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

sealed interface ShoppingListCardEvent {
    data class Click(val item: ShoppingListItem) : ShoppingListCardEvent
    data class Edit(val item: ShoppingListItem) : ShoppingListCardEvent
    data class Copy(val item: ShoppingListItem) : ShoppingListCardEvent
    data class ChangeIcon(val item: ShoppingListItem) : ShoppingListCardEvent
    data class Delete(val item: ShoppingListItem) : ShoppingListCardEvent
}
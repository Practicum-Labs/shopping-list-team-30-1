package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import io.dimasla4ee.shoppinglist.core.mvi.MviEffect

sealed interface ShoppingListsEffect : MviEffect {

    data class NavigateToProducts(
        val listId: Long,
        val listName: String
    ) : ShoppingListsEffect
}
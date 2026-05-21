package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state

sealed class ShoppingListDialog {

    data object None : ShoppingListDialog()

    data object Create : ShoppingListDialog()

    data class Rename(
        val id: Long,
        val value: String
    ) : ShoppingListDialog()

    data class Delete(
        val id: Long,
        val name: String
    ) : ShoppingListDialog()

    data object DeleteAll : ShoppingListDialog()

    data object Logout : ShoppingListDialog()
}
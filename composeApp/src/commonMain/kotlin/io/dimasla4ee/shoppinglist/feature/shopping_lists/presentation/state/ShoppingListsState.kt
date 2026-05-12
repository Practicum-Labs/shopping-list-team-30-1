package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList

data class ShoppingListsState(
    val lists: List<ShoppingList> = emptyList(),

    val dialog: ShoppingListDialog = ShoppingListDialog.None,

    val newListName: String = "",

    val isIconSheetVisible: Boolean = false,
    val selectedListId: Long? = null,

    val searchQuery: String = "",
    val isSearchMode: Boolean = false,

    val renameValue: String = "",
    val renameTargetId: Long? = null,

    val deleteTargetId: Long? = null,

    val isFabVisible: Boolean = true
)
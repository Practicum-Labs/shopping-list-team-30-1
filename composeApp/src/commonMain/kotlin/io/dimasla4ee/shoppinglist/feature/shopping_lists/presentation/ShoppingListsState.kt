package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList

data class ShoppingListsState(
    val lists: List<ShoppingList> = emptyList(),

    val isDialogVisible: Boolean = false,
    val newListName: String = "",

    val isIconSheetVisible: Boolean = false,
    val selectedListId: Long? = null,

    val isDeleteAllDialogVisible: Boolean = false,

    val searchQuery: String = "",
    val isSearchMode: Boolean = false,

    val isRenameDialogVisible: Boolean = false,
    val renameValue: String = "",
    val renameTargetId: Long? = null,

    val isDeleteDialogVisible: Boolean = false,
    val deleteTargetId: Long? = null
)
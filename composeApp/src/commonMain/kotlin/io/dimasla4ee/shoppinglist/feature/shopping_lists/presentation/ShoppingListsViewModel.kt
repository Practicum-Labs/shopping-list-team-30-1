package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import kotlinx.coroutines.launch

class ShoppingListsViewModel(
    private val interactor: ShoppingListsInteractor
) : ViewModel() {

    var state by mutableStateOf(ShoppingListsState())
        private set

    init {
        observeLists()
    }

    private fun observeLists() {
        viewModelScope.launch {
            interactor.getShoppingLists().collect { lists ->
                state = state.copy(
                    lists = lists
                )
            }
        }
    }

    fun onFabClick() {
        state = state.copy(isDialogVisible = true)
    }

    fun onDialogDismiss() {
        state = state.copy(
            isDialogVisible = false,
            newListName = ""
        )
    }

    fun onNameChange(value: String) {
        state = state.copy(newListName = value)
    }

    fun onCreateList() {
        val name = state.newListName.trim()
        if (name.isEmpty()) return

        viewModelScope.launch {
            state = state.copy(
                isDialogVisible = false,
                newListName = ""
            )
        }
    }

    fun onCardEvent(event: ShoppingListCardEvent) {
        when (event) {
            is ShoppingListCardEvent.Delete -> {
                state = state.copy(
                    isDeleteDialogVisible = true,
                    deleteTargetId = event.item.id
                )
            }

            is ShoppingListCardEvent.Copy -> {
                val target = state.lists.find {
                    it.id == event.item.id
                } ?: return

                viewModelScope.launch {
                    interactor.duplicateShoppingList(target)
                }
            }

            is ShoppingListCardEvent.Edit -> {
                state = state.copy(
                    isRenameDialogVisible = true,
                    renameValue = event.item.name,
                    renameTargetId = event.item.id
                )
            }

            is ShoppingListCardEvent.ChangeIcon -> {
                onIconClick(event.item.id)
            }

            is ShoppingListCardEvent.Click -> {
                // TODO
            }
        }
    }

    private fun onIconClick(listId: Long) {
        state = state.copy(
            isIconSheetVisible = true,
            selectedListId = listId
        )
    }

    fun onSheetDismiss() {
        state = state.copy(
            isIconSheetVisible = false,
            selectedListId = null
        )
    }

    fun onIconSelected(icon: ShoppingListIcon) {

        val selectedId = state.selectedListId ?: return

        val targetList = state.lists.find {
            it.id == selectedId
        } ?: return

        viewModelScope.launch {
            interactor.updateShoppingList(
                targetList.copy(icon = icon)
            )
        }

        state = state.copy(
            isIconSheetVisible = false,
            selectedListId = null
        )
    }

    fun onDeleteAllClick() {
        state = state.copy(
            isDeleteAllDialogVisible = true
        )
    }

    fun onDeleteAllDismiss() {
        state = state.copy(
            isDeleteAllDialogVisible = false
        )
    }

    fun onDeleteAllConfirm() {
        viewModelScope.launch {
            interactor.deleteAllShoppingLists()
            state = state.copy(
                isDeleteAllDialogVisible = false
            )
        }
    }

    fun onSearchClick() {
        state = state.copy(
            isSearchMode = true
        )
    }

    fun onSearchDismiss() {
        state = state.copy(
            isSearchMode = false,
            searchQuery = ""
        )
    }

    fun onSearchQueryChange(value: String) {
        state = state.copy(
            searchQuery = value
        )
    }

    val visibleLists: List<ShoppingList>
        get() {
            val query = state.searchQuery.trim()
            if (query.isEmpty()) {
                return state.lists
            }
            return state.lists.filter { list ->
                list.name.contains(query, ignoreCase = true)
            }
        }

    fun onRenameValueChange(value: String) {
        state = state.copy(
            renameValue = value
        )
    }

    fun onRenameDismiss() {
        state = state.copy(
            isRenameDialogVisible = false,
            renameValue = "",
            renameTargetId = null
        )
    }

    fun onRenameConfirm() {
        val id = state.renameTargetId
        val name = state.renameValue.trim()

        val oldList = state.lists.find { it.id == id }

        if (
            id != null &&
            name.isNotEmpty() &&
            oldList != null
        ) {
            viewModelScope.launch {
                interactor.updateShoppingList(
                    oldList.copy(name = name)
                )
            }
            onRenameDismiss()
        }
    }

    fun onDeleteDismiss() {
        state = state.copy(
            isDeleteDialogVisible = false,
            deleteTargetId = null
        )
    }

    fun onDeleteConfirm() {
        val targetId = state.deleteTargetId ?: return
        val target = state.lists.find {
            it.id == targetId
        } ?: return

        viewModelScope.launch {
            interactor.deleteShoppingList(target)
            state = state.copy(
                isDeleteDialogVisible = false,
                deleteTargetId = null
            )
        }
    }
}
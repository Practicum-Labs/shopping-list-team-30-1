package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListsState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ShoppingListsViewModel(
    private val interactor: ShoppingListsInteractor
) : ViewModel() {

    var state by mutableStateOf(ShoppingListsState())
        private set

    private var observeJob: Job? = null

    fun observeLists() {
        if (observeJob != null) return

        observeJob = viewModelScope.launch {
            interactor.getShoppingLists().collect { lists ->
                state = state.copy(
                    lists = lists
                )
            }
        }
    }

    fun onFabClick() {
        state = state.copy(dialog = ShoppingListDialog.Create)
    }

    fun onDeleteAllClick() {
        state = state.copy(dialog = ShoppingListDialog.DeleteAll)
    }

    fun onCardEvent(event: ShoppingListCardEvent) {
        when (event) {
            is ShoppingListCardEvent.Delete -> {
                state = state.copy(
                    dialog = ShoppingListDialog.Delete(event.item.id),
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
                    dialog = ShoppingListDialog.Rename(
                        id = event.item.id,
                        value = event.item.name
                    ),
                    renameValue = event.item.name
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
        state = state.copy(selectedListId = listId)
    }

    fun onDialogDismiss() {
        state = state.copy(
            dialog = ShoppingListDialog.None,
            newListName = "",
            renameValue = "",
            selectedListId = null
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
                dialog = ShoppingListDialog.None,
                newListName = ""
            )
        }
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

    fun onDeleteAllConfirm() {
        viewModelScope.launch {
            interactor.deleteAllShoppingLists()
            onDialogDismiss()
        }
    }

    fun onSearchClick() {
        state = state.copy(
            isSearchMode = true,
            isFabVisible = false
        )
    }

    fun onSearchDismiss() {
        state = state.copy(
            isSearchMode = false,
            isFabVisible = true,
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
        state = state.copy(renameValue = value)
    }

    fun onRenameConfirm() {
        val dialog = state.dialog as? ShoppingListDialog.Rename ?: return
        val name = state.renameValue.trim()

        val oldList = state.lists.find { it.id == dialog.id } ?: return

        if (name.isNotEmpty()) {
            viewModelScope.launch {
                interactor.updateShoppingList(
                    oldList.copy(name = name)
                )
            }
            onDialogDismiss()
        }
    }

    fun onDeleteConfirm() {
        val dialog = state.dialog as? ShoppingListDialog.Delete ?: return

        val target = state.lists.find { it.id == dialog.id } ?: return

        viewModelScope.launch {
            interactor.deleteShoppingList(target)
            onDialogDismiss()
        }
    }
}
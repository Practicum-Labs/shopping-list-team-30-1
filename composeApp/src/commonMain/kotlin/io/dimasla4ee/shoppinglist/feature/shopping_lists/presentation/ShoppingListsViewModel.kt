package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon

class ShoppingListsViewModel : ViewModel() {

    var state by mutableStateOf(ShoppingListsState())
        private set

    // FAB
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

        val newList = ShoppingList(
            id = System.currentTimeMillis(),
            name = name,
            icon = ShoppingListIcon.SHOPPING_CART,
            products = emptyList()
        )

        state = state.copy(
            lists = state.lists + newList,
            isDialogVisible = false,
            newListName = ""
        )
    }

    // события карточки
    fun onCardEvent(event: ShoppingListCardEvent) {
        when (event) {

            is ShoppingListCardEvent.Delete -> {
                state = state.copy(
                    lists = state.lists.filterNot { it.id == event.item.id }
                )
            }

            is ShoppingListCardEvent.Copy -> {
                val original = state.lists.find { it.id == event.item.id } ?: return

                val newList = original.copy(
                    id = System.currentTimeMillis(),
                    name = "${original.name} (копия)"
                )

                state = state.copy(
                    lists = state.lists + newList
                )
            }

            is ShoppingListCardEvent.Edit -> {
                // TODO
            }

            is ShoppingListCardEvent.ChangeIcon -> {
                // TODO
            }

            is ShoppingListCardEvent.Click -> {
                // TODO
            }
        }
    }
}
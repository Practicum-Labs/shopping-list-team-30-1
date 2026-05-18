package io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

sealed interface ShoppingListsIntent : MviIntent {

    data object ObserveLists : ShoppingListsIntent

    data object FabClick : ShoppingListsIntent

    data object DialogDismiss : ShoppingListsIntent

    data class NameChanged(
        val value: String
    ) : ShoppingListsIntent

    data object CreateList : ShoppingListsIntent

    data object DeleteAllClick : ShoppingListsIntent

    data object DeleteAllConfirm: ShoppingListsIntent

    data object SearchClick: ShoppingListsIntent

    data object SearchDismiss: ShoppingListsIntent

    data class SearchQueryChanged(
        val value: String
    ) : ShoppingListsIntent

    data class RenameValueChanged(
        val value: String
    ) : ShoppingListsIntent

    data object RenameConfirm : ShoppingListsIntent

    data object DeleteConfirm : ShoppingListsIntent

    data class IconSelected(
        val icon: ShoppingListIcon
    ) : ShoppingListsIntent

    data object SheetDismiss : ShoppingListsIntent

    data class ListClicked(
        val item: ShoppingListItem
    ) : ShoppingListsIntent

    data class EditClicked(
        val item: ShoppingListItem
    ) : ShoppingListsIntent

    data class CopyClicked(
        val item: ShoppingListItem
    ) : ShoppingListsIntent

    data class ChangeIconClicked(
        val item: ShoppingListItem
    ) : ShoppingListsIntent

    data class DeleteClicked(
        val item: ShoppingListItem
    ) : ShoppingListsIntent
}
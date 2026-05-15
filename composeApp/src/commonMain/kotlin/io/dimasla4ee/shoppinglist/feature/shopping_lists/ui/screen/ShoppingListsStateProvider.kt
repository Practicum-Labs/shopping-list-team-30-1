package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListDialog
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.state.ShoppingListsState

class ShoppingListsStateProvider : PreviewParameterProvider<ShoppingListsState> {
    override val values = sequenceOf(

        ShoppingListsState(),

        ShoppingListsState(
            lists = listOf(
                ShoppingList(
                    id = 1,
                    name = "Продукты",
                    icon = ShoppingListIcon.SHOPPING_CART,
                    products = emptyList()
                ),
                ShoppingList(
                    id = 2,
                    name = "Аптека",
                    icon = ShoppingListIcon.MEDICATION,
                    products = emptyList()
                )
            )
        ),

        ShoppingListsState(
            lists = List(10) {
                ShoppingList(
                    id = it.toLong(),
                    name = "Список $it",
                    icon = ShoppingListIcon.SHOPPING_CART,
                    products = emptyList()
                )
            }
        ),

        ShoppingListsState(
            lists = sampleLists(),
            dialog = ShoppingListDialog.Create,
            newListName = "Новый список"
        ),

        ShoppingListsState(
            lists = sampleLists(),
            isIconSheetVisible = true,
            selectedListId = 1
        ),

        ShoppingListsState(
            lists = sampleLists(),
            dialog = ShoppingListDialog.DeleteAll
        ),

        ShoppingListsState(
            lists = sampleLists(),
            dialog = ShoppingListDialog.Delete(
                id = 1
            )
        ),

        ShoppingListsState(
            lists = sampleLists(),
            dialog = ShoppingListDialog.Rename(
                id = 1,
                value = "Фрукты"
            ),
            renameValue = "Фрукты"
        ),

        ShoppingListsState(
            lists = sampleLists(),
            isSearchMode = true,
            searchQuery = "Про"
        ),

        // Search mode empty
        ShoppingListsState(
            lists = sampleLists(),
            isSearchMode = true,
            searchQuery = "zzz"
        ),
    )

    private fun sampleLists() = listOf(
        ShoppingList(
            id = 1,
            name = "Продукты",
            icon = ShoppingListIcon.SHOPPING_CART,
            products = emptyList()
        ),
        ShoppingList(
            id = 2,
            name = "Аптека",
            icon = ShoppingListIcon.MEDICATION,
            products = emptyList()
        ),
        ShoppingList(
            id = 3,
            name = "Подарки",
            icon = ShoppingListIcon.PRESENT,
            products = emptyList()
        )
    )
}
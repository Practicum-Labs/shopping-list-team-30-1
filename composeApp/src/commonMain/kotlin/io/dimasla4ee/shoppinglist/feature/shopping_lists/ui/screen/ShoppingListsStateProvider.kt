package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.screen

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsState

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
            lists = listOf(
                ShoppingList(
                    id = 1,
                    name = "Продукты",
                    icon = ShoppingListIcon.SHOPPING_CART,
                    products = emptyList()
                )
            ),
            isDialogVisible = true,
            newListName = "Новый список"
        )
    )
}
package io.dimasla4ee.shoppinglist.feature.shopping_lists.domain

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ShoppingListsInteractorImpl(
    private val repository: ShoppingListsRepository,
    private val productRepository: ProductRepository
) : ShoppingListsInteractor {

    override fun getShoppingLists(): Flow<List<ShoppingList>> {
        return repository.getShoppingLists()
    }

    override suspend fun addShoppingList(name: String) {
        val shoppingList = ShoppingList(
            id = 0,
            name = name,
            icon = ShoppingListIcon.SHOPPING_CART,
            products = emptyList()
        )

        repository.addShoppingList(shoppingList)
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        repository.deleteShoppingList(shoppingList)
    }

    override suspend fun deleteAllShoppingLists() {
        repository
            .getShoppingLists()
            .first()
            .forEach { repository.deleteShoppingList(it) }
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        repository.updateShoppingList(shoppingList)
    }

    override suspend fun duplicateShoppingList(shoppingList: ShoppingList) {
        val newListId = repository.addShoppingListAndReturnId(
            shoppingList.copy(
                id = 0,
                name = "${shoppingList.name} (копия)"
            )
        )

        val copiedProducts = productRepository.getProductsOfListOnce(
            shoppingList.id).map { product ->
                product.copy(
                    id = 0,
                    listId = newListId
                )
        }

        productRepository.addProducts(
            copiedProducts
        )
    }
}
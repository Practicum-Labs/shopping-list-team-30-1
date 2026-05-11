package io.dimasla4ee.shoppinglist.feature.shopping_lists.data

import io.dimasla4ee.shoppinglist.core.database.dao.ShoppingListDao
import io.dimasla4ee.shoppinglist.core.database.mapper.toDomain
import io.dimasla4ee.shoppinglist.core.database.mapper.toEntity
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ShoppingListsRepositoryImpl(
    private val dao: ShoppingListDao
) : ShoppingListsRepository {

    override fun getShoppingLists(): Flow<List<ShoppingList>> {
        return dao.getShoppingLists().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addShoppingList(shoppingList: ShoppingList) {
        dao.addShoppingList(shoppingList.toEntity())
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        dao.deleteShoppingList(shoppingList.toEntity())
    }

    override suspend fun deleteAllShoppingLists() {
        dao.getShoppingLists()
            .first()
            .forEach { dao.deleteShoppingList(it) }
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        dao.addShoppingList(shoppingList.toEntity())
    }
}
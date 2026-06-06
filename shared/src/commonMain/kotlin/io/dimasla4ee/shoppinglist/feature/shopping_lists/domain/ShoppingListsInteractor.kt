package io.dimasla4ee.shoppinglist.feature.shopping_lists.domain

import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingList
import kotlinx.coroutines.flow.Flow

interface ShoppingListsInteractor {

    fun getShoppingLists(): Flow<List<ShoppingList>>

    suspend fun addShoppingList(name: String)

    suspend fun deleteShoppingList(shoppingList: ShoppingList)

    suspend fun deleteAllShoppingLists()

    suspend fun updateShoppingList(shoppingList: ShoppingList)

    suspend fun duplicateShoppingList(shoppingList: ShoppingList)
}
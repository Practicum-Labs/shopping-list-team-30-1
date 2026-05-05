package io.dimasla4ee.shoppinglist.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.dimasla4ee.shoppinglist.core.database.entity.ProductEntity
import io.dimasla4ee.shoppinglist.core.database.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShoppingList(shoppingListEntity: ShoppingListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM ShoppingListEntity ORDER BY addedAtMillis DESC")
    fun getShoppingLists(): Flow<List<ShoppingListEntity>>

    @Query("SELECT * FROM ProductEntity ORDER BY addedAtMillis DESC")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM ProductEntity WHERE listId = :listId ORDER BY addedAtMillis DESC")
    fun getProductsOfList(listId: Long): Flow<List<ProductEntity>>

    @Delete
    suspend fun deleteShoppingList(shoppingListEntity: ShoppingListEntity)

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)
}
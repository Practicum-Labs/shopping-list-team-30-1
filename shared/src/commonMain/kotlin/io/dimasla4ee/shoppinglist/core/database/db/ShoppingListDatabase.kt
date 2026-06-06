package io.dimasla4ee.shoppinglist.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.dimasla4ee.shoppinglist.core.database.dao.ShoppingListDao
import io.dimasla4ee.shoppinglist.core.database.entity.ProductEntity
import io.dimasla4ee.shoppinglist.core.database.entity.ShoppingListEntity

@Database(
    version = 4,
    entities = [ShoppingListEntity::class, ProductEntity::class]
)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
}
package io.dimasla4ee.shoppinglist.app.di

import androidx.room.Room
import androidx.room.RoomDatabase
import io.dimasla4ee.shoppinglist.core.config.DatabaseConfig
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformDataModule = module {

    factory< RoomDatabase.Builder<ShoppingListDatabase>> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = ShoppingListDatabase::class.java,
            name = DatabaseConfig.SHOPPING_LIST_DATABASE_NAME
        )
    }
}

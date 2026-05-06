package io.dimasla4ee.shoppinglist.app.di

import androidx.room.Room
import androidx.room.RoomDatabase
import io.dimasla4ee.shoppinglist.core.config.DatabaseConfig
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import org.koin.dsl.module
import java.io.File

actual val platformDataModule = module {

    factory<RoomDatabase.Builder<ShoppingListDatabase>> {
        val dbFile = File(
            System.getProperty("java.io.tmpdir"),
            DatabaseConfig.SHOPPING_LIST_DATABASE_NAME
        )

        Room.databaseBuilder(
            name = dbFile.absolutePath
        )
    }
}
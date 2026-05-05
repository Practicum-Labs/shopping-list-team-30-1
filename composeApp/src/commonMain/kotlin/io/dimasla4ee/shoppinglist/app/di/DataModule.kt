package io.dimasla4ee.shoppinglist.app.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости Repository и Data sources.
 */
val dataModule = module {
    single<ShoppingListDatabase> {
        val builder: RoomDatabase.Builder<ShoppingListDatabase> = get()

        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Repository и Data sources.
 */
expect val platformDataModule: Module
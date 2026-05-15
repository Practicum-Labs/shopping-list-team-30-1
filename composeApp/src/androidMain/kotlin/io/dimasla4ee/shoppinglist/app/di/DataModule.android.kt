package io.dimasla4ee.shoppinglist.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import io.dimasla4ee.shoppinglist.app.data.database.SettingsDataSource
import io.dimasla4ee.shoppinglist.app.data.repository.SettingsRepositoryImpl
import io.dimasla4ee.shoppinglist.core.config.DatabaseConfig
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
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

    single<SharedPreferences> {

        androidContext().getSharedPreferences(
            "settings",
            Context.MODE_PRIVATE
        )
    }

    single {
        SettingsDataSource(get())
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(get())
    }
}

package io.dimasla4ee.shoppinglist.app.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import io.dimasla4ee.shoppinglist.core.config.DatabaseConfig
import io.dimasla4ee.shoppinglist.app.data.dataStore.createDataStore
import io.dimasla4ee.shoppinglist.app.data.repository.SettingsRepositoryJVM
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
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

    single<DataStore<Preferences>> {
        createDataStore(
            dataDirPath = System.getProperty("user.home") + "/.shoppinglist"
        ).also {
            val dataDir = File(System.getProperty("user.home"), ".shoppinglist")
            if (!dataDir.exists()) {
                dataDir.mkdirs()
            }
        }
    }

    single<SettingsRepository> {
        SettingsRepositoryJVM(get())
    }

}

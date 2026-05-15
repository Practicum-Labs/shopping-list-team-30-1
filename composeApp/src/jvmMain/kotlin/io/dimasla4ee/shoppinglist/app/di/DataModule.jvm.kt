package io.dimasla4ee.shoppinglist.app.di

import androidx.room.Room
import androidx.room.RoomDatabase
import io.dimasla4ee.shoppinglist.core.config.DatabaseConfig
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    single<SettingsRepository> {
        InMemorySettingsRepository()
    }

}

@Deprecated(
    message = "Временная in-memory заглушка для JVM, добавленная для фикса краша Koin. " +
            "Необходимо удалить этот класс после выполнения таски по миграции настроек на DataStore."
)
class InMemorySettingsRepository : SettingsRepository {

    private val darkThemeState = MutableStateFlow(false)

    override val isDarkTheme: Flow<Boolean> =
        darkThemeState.asStateFlow()

    override suspend fun setDarkTheme(enabled: Boolean) {
        darkThemeState.value = enabled
    }
}
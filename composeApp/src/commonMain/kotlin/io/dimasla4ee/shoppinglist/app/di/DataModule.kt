package io.dimasla4ee.shoppinglist.app.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import de.jensklingenberg.ktorfit.Ktorfit
import io.dimasla4ee.shoppinglist.core.data.network.api.AuthApi
import io.dimasla4ee.shoppinglist.core.data.network.client.KtorfitNetworkClient
import io.dimasla4ee.shoppinglist.core.data.network.client.NetworkClient
import io.dimasla4ee.shoppinglist.core.data.repository.AuthRepositoryImpl
import io.dimasla4ee.shoppinglist.core.data.repository.SettingsRepositoryImpl
import io.dimasla4ee.shoppinglist.core.data.repository.TokenStorageImpl
import io.dimasla4ee.shoppinglist.core.database.dao.ShoppingListDao
import io.dimasla4ee.shoppinglist.core.database.db.ShoppingListDatabase
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.core.domain.storage.TokenStorage
import io.dimasla4ee.shoppinglist.feature.shopping_lists.data.ShoppingListsRepositoryImpl
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val BASE_URL = "https://practicumopbackend-production.up.railway.app/"

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

    single<ShoppingListDao> {
        get<ShoppingListDatabase>().shoppingListDao()
    }

    single<ShoppingListsRepository> {
        ShoppingListsRepositoryImpl(
            dao = get()
        )
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
        }
    }

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(get<Json>())
            }
        }
    }

    single<Ktorfit> {
        Ktorfit.Builder()
            .baseUrl(BASE_URL)
            .httpClient(get<HttpClient>())
            .build()
    }

    single<AuthApi> {
        get<Ktorfit>().create()
    }

    single<NetworkClient> {
        KtorfitNetworkClient(api = get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            networkClient = get(),
            tokenStorage = get()
        )
    }

    singleOf(::SettingsRepositoryImpl) bind SettingsRepository::class
    singleOf(::TokenStorageImpl) bind TokenStorage::class
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Repository и Data sources.
 */
expect val platformDataModule: Module
package io.dimasla4ee.shoppinglist.app.di

import io.dimasla4ee.shoppinglist.core.domain.interactor.GetThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.ToggleThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractorImpl
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости Interactor/Usecases.
 */
val domainModule = module {

    factory<GetThemeInteractor> {
        GetThemeInteractor(
            repository = get<SettingsRepository>()
        )
    }

    factory<ToggleThemeInteractor> {
        ToggleThemeInteractor(
            repository = get<SettingsRepository>()
        )
    }

    single<ShoppingListsInteractor> {
        ShoppingListsInteractorImpl(
            repository = get<ShoppingListsRepository>()
        )
    }
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Interactor/Usecases.
 */
expect val platformDomainModule: Module
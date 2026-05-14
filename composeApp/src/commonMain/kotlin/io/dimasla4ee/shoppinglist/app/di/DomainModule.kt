package io.dimasla4ee.shoppinglist.app.di

import io.dimasla4ee.shoppinglist.core.domain.interactor.GetThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.ToggleThemeInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости Interactor/Usecases.
 */
val domainModule = module {

    factory {
        GetThemeInteractor(get())
    }

    factory {
        ToggleThemeInteractor(get())
    }

    single<ShoppingListsInteractor> {
        ShoppingListsInteractorImpl(
            repository = get()
        )
    }
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Interactor/Usecases.
 */
expect val platformDomainModule: Module
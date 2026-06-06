package io.dimasla4ee.shoppinglist.app.di

import io.dimasla4ee.shoppinglist.core.domain.interactor.auth.LoginUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.auth.RecoverPasswordUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.auth.RegisterUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.GetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.RemoveSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.SetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.theme.GetThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.theme.ToggleThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.CheckTokenUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.ClearAuthTokensUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.GetAccessTokenUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.GetRefreshTokenUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.ObserveSessionUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.RefreshSessionUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.token.SaveAuthTokensUseCase
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.ProductInteractor
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.ProductInteractorImpl
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractorImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
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

    singleOf(::ShoppingListsInteractorImpl) {
        bind<ShoppingListsInteractor>()
    }

    factoryOf(::SaveAuthTokensUseCase)
    factoryOf(::GetAccessTokenUseCase)
    factoryOf(::GetRefreshTokenUseCase)
    factoryOf(::ClearAuthTokensUseCase)

    factoryOf(::RegisterUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::RefreshSessionUseCase)
    factoryOf(::ObserveSessionUseCase)
    factoryOf(::RecoverPasswordUseCase)
    factoryOf(::CheckTokenUseCase)

    factoryOf(::SetSortModeUseCase)
    factoryOf(::GetSortModeUseCase)
    factoryOf(::RemoveSortModeUseCase)

    singleOf(::ProductInteractorImpl) {
        bind<ProductInteractor>()
    }
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Interactor/Usecases.
 */
expect val platformDomainModule: Module
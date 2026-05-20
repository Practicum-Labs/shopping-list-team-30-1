package io.dimasla4ee.shoppinglist.app.di

import io.dimasla4ee.shoppinglist.core.domain.interactor.CheckTokenUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.GetThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.LoginUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.RecoverPasswordUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.RefreshTokenUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.RegisterUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.ToggleThemeInteractor
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.GetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.RemoveSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.interactor.sorting.SetSortModeUseCase
import io.dimasla4ee.shoppinglist.core.domain.repository.AuthRepository
import io.dimasla4ee.shoppinglist.core.domain.repository.SettingsRepository
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractor
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsInteractorImpl
import io.dimasla4ee.shoppinglist.feature.shopping_lists.domain.ShoppingListsRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
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

    factory<RegisterUseCase> {
        RegisterUseCase(
            authRepository = get<AuthRepository>()
        )
    }

    factory<LoginUseCase> {
        LoginUseCase(
            authRepository = get<AuthRepository>()
        )
    }

    factory<RefreshTokenUseCase> {
        RefreshTokenUseCase(
            authRepository = get<AuthRepository>()
        )
    }

    factory<RecoverPasswordUseCase> {
        RecoverPasswordUseCase(
            authRepository = get<AuthRepository>()
        )
    }

    factory<CheckTokenUseCase> {
        CheckTokenUseCase(
            authRepository = get<AuthRepository>()
        )
    }

    factoryOf(::SetSortModeUseCase)
    factoryOf(::GetSortModeUseCase)
    factoryOf(::RemoveSortModeUseCase)
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Interactor/Usecases.
 */
expect val platformDomainModule: Module
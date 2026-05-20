package io.dimasla4ee.shoppinglist.app.di

import io.dimasla4ee.shoppinglist.app.navigation.NavigationViewModel
import io.dimasla4ee.shoppinglist.core.presentation.settings.SettingsViewModel
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.recover_password.RecoverPasswordViewModel
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.register.RegisterViewModel
import io.dimasla4ee.shoppinglist.feature.authorization.presentation.sign_in.SignInViewModel
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model.ProductsViewModel
import io.dimasla4ee.shoppinglist.feature.shopping_lists.presentation.ShoppingListsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости UI и ViewModel.
 */
val presentationModule = module {

    viewModel<NavigationViewModel> {
        NavigationViewModel()
    }

    viewModel {
        SettingsViewModel(
            getThemeUseCase = get(),
            toggleThemeUseCase = get()
        )
    }

    viewModelOf(::ShoppingListsViewModel)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RecoverPasswordViewModel)
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости UI и ViewModel.
 */
expect val platformPresentationModule: Module

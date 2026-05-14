package io.dimasla4ee.shoppinglist.app.di

import org.koin.core.module.dsl.viewModel
import io.dimasla4ee.shoppinglist.core.presentation.settings.SettingsViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости UI и ViewModel.
 */
val presentationModule = module {

    viewModel {
        SettingsViewModel(
            getThemeUseCase = get(),
            toggleThemeUseCase = get()
        )
    }
}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости UI и ViewModel.
 */
expect val platformPresentationModule: Module

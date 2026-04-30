package io.dimasla4ee.shoppinglist.app.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости UI и ViewModel.
 */
val presentationModule = module {}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости UI и ViewModel.
 */
expect val platformPresentationModule: Module

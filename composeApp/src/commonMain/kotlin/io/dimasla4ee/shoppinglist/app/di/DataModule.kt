package io.dimasla4ee.shoppinglist.app.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости Repository и Data sources.
 */
val dataModule = module {}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Repository и Data sources.
 */
expect val platformDataModule: Module
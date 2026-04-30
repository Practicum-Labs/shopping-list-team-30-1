package io.dimasla4ee.shoppinglist.app.di

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Модуль Koin, отвечающий за зависимости Interactor/Usecases.
 */
val domainModule = module {}

/**
 * Модуль Koin, отвечающий за платформенно-специфичные зависимости Interactor/Usecases.
 */
expect val platformDomainModule: Module
package io.dimasla4ee.shoppinglist.app.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(config: KoinAppDeclaration? = null): KoinApplication = startKoin {
    includes(config)
    modules(
        dataModule,
        platformDataModule,
        domainModule,
        platformDomainModule,
        presentationModule,
        platformPresentationModule
    )
}
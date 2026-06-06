package io.dimasla4ee.shoppinglist.app.startup.session.splash.domain

sealed interface SplashDestination {

    data object Welcome : SplashDestination
    data object ShoppingLists : SplashDestination
}
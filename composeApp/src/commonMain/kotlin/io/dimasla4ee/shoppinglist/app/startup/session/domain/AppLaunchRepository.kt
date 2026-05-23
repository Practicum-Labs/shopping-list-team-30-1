package io.dimasla4ee.shoppinglist.app.startup.session.domain

interface AppLaunchRepository {
    suspend fun isFirstLaunch(): Boolean
    suspend fun setLaunched()
}
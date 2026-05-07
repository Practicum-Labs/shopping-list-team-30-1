package io.dimasla4ee.shoppinglist.app.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object Onboard : Route

    @Serializable
    data object Main : Route

    @Serializable
    data object Products : Route

    @Serializable
    data object Authorization : Route

    @Serializable
    data object Registration : Route

    @Serializable
    data object PasswordRecovery : Route

}
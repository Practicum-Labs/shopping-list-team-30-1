package io.dimasla4ee.shoppinglist.app.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object Splash : Route

    @Serializable
    data object Welcome : Route
    @Serializable
    data object ShoppingLists : Route

    @Serializable
    data class ProductsList(
        val listId: Long,
        val listName: String
    ) : Route

    @Serializable
    data object Authorization : Route

    @Serializable
    data object Registration : Route

    @Serializable
    data object PasswordRecovery : Route

}
package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import org.jetbrains.compose.resources.DrawableResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.img_main_screen
import shoppinglist.composeapp.generated.resources.img_main_screen_dark
import shoppinglist.composeapp.generated.resources.img_product_list
import shoppinglist.composeapp.generated.resources.img_product_list_dark
import shoppinglist.composeapp.generated.resources.img_search_screen
import shoppinglist.composeapp.generated.resources.img_search_screen_dark
import shoppinglist.composeapp.generated.resources.img_shopping_lists
import shoppinglist.composeapp.generated.resources.img_shopping_lists_dark

class AppPlaceholders(
    val imgMainScreen: DrawableResource,
    val imgProductList: DrawableResource,
    val imgSearchScreen: DrawableResource,
    val imgShoppingLists: DrawableResource
)

@Suppress("CompositionLocalAllowlist")
val LocalAppPlaceholders = staticCompositionLocalOf<AppPlaceholders> {
    error("No AppPlaceholders provided")
}

val LightAppPlaceholders = AppPlaceholders(
    imgMainScreen = Res.drawable.img_main_screen,
    imgProductList = Res.drawable.img_product_list,
    imgSearchScreen = Res.drawable.img_search_screen,
    imgShoppingLists = Res.drawable.img_shopping_lists
)

val DarkAppPlaceholders = AppPlaceholders(
    imgMainScreen = Res.drawable.img_main_screen_dark,
    imgProductList = Res.drawable.img_product_list_dark,
    imgSearchScreen = Res.drawable.img_search_screen_dark,
    imgShoppingLists = Res.drawable.img_shopping_lists_dark
)

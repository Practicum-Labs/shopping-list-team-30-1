package io.dimasla4ee.shoppinglist

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.dimasla4ee.shoppinglist.app.App
import io.dimasla4ee.shoppinglist.app.di.initKoin

fun main() = application {
    initKoin {
        printLogger()
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "ShoppingList",
    ) {
        App()
    }
}
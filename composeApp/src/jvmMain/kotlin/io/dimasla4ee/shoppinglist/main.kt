package io.dimasla4ee.shoppinglist

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ShoppingList",
    ) {
        App()
    }
}
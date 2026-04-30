package io.dimasla4ee.shoppinglist.app

import io.dimasla4ee.shoppinglist.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
package io.dimasla4ee.shoppinglist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

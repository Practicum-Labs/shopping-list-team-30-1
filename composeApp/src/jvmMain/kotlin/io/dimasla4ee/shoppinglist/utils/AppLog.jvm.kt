package io.dimasla4ee.shoppinglist.utils

actual object AppLog {
    actual fun d(tag: String, message: String) {
        println("$tag : $message")
    }
}
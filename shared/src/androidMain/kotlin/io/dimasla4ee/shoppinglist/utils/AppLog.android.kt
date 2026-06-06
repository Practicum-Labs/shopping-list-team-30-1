package io.dimasla4ee.shoppinglist.utils

import android.util.Log

actual object AppLog {
    actual fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}
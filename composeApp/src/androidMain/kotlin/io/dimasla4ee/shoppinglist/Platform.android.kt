package io.dimasla4ee.shoppinglist

import android.os.Build

@Suppress("MatchingDeclarationName")
class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
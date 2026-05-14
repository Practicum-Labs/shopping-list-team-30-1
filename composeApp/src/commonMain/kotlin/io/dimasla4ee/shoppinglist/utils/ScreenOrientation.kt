package io.dimasla4ee.shoppinglist.utils

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable

enum class ScreenOrientation { PORTRAIT, LANDSCAPE }

@Composable
fun OrientationProvider(
    content: @Composable (orientation: ScreenOrientation) -> Unit
) {
    BoxWithConstraints {
        val orientation = if (maxWidth > maxHeight) {
            ScreenOrientation.LANDSCAPE
        } else {
            ScreenOrientation.PORTRAIT
        }
        content(orientation)
    }
}
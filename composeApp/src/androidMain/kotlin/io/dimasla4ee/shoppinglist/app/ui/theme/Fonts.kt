package io.dimasla4ee.shoppinglist.app.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import io.dimasla4ee.shoppinglist.R

actual val AppFontFamily: FontFamily = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.Normal),
    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(
        resId = R.font.caveat,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    )
)
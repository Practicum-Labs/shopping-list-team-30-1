package io.dimasla4ee.shoppinglist.core.utils

fun Float.toFormattedString() = if (this == toInt().toFloat()) {
    toInt().toString()
} else {
    toString()
        .trimEnd('0')
        .trimEnd('.')
}
package io.dimasla4ee.shoppinglist.core.utils

fun CharSequence.isValidEmail(): Boolean = Regex(".+@.+").matches(this)
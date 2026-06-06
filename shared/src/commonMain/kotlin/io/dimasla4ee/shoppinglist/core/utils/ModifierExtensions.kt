package io.dimasla4ee.shoppinglist.core.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.appDefaultFormSize(): Modifier = this
    .widthIn(max = 350.dp)
    .fillMaxWidth()
    .wrapContentHeight()
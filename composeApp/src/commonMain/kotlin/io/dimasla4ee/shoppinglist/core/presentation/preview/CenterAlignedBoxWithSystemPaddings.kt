package io.dimasla4ee.shoppinglist.core.presentation.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenterAlignedBoxWithSystemPaddings(
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    Scaffold(modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}
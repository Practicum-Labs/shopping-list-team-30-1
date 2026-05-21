package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun AppFloatingActionButton(
    iconRes: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            painter = iconRes,
            contentDescription = null
        )
    }
}

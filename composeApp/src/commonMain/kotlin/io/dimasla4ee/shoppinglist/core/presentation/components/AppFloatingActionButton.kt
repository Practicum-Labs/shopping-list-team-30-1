package io.dimasla4ee.shoppinglist.core.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions

@Composable
fun AppFloatingActionButton(
    iconRes: Painter,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = contentColorFor(containerColor)
) {
    onClick?.let {
        FloatingActionButton(
            modifier = modifier,
            shape = RoundedCornerShape(AppDimensions.paddingMedium),
            onClick = onClick,
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            Icon(
                painter = iconRes,
                contentDescription = contentDescription,
            )
        }
    }
}
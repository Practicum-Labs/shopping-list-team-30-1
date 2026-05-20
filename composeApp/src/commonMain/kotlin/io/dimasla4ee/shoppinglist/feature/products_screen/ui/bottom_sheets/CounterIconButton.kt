package io.dimasla4ee.shoppinglist.feature.products_screen.ui.bottom_sheets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_minus_24

@Composable
fun CounterIconButton(
    icon: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        modifier = Modifier
            .padding(top = AppDimensions.paddingExtraSmall)
            .size(AppDimensions.topAppIconSize),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            onClick = onClick,
            enabled = enabled,
            shape = CircleShape,

            color = if (enabled) {
                MaterialTheme.colorScheme.tertiaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },

            modifier = modifier.size(AppDimensions.areaOfIcon)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = contentDescription,

                    tint = if (enabled) {
                        MaterialTheme.colorScheme.onTertiaryContainer
                    } else {
                        MaterialTheme.colorScheme.outline
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun CounterIconButtonPreview() {
    MaterialTheme {
        CounterIconButton(
            icon = Res.drawable.ic_minus_24,
            contentDescription = "",
            onClick = {},
            enabled = true
        )
    }
}
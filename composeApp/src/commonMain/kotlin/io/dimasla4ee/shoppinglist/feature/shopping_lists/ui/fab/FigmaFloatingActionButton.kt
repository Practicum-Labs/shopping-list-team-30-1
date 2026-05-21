package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.fab

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_fab_24

@Composable
fun FigmaFloatingActionButton(
    onAddListClick: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    if (onAddListClick != null) {
        FloatingActionButton(
            modifier = modifier.offset(y = (-AppDimensions.paddingMedium)),
            shape = RoundedCornerShape(AppDimensions.paddingMedium),
            onClick = onAddListClick,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_fab_24),
                contentDescription = null,
            )
        }
    }
}
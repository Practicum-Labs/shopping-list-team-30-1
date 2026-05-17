package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import org.jetbrains.compose.resources.painterResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.ic_check_24

@Composable
fun CircleCheckButton(
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(
                if (checked) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape
            )
            .clickable(onClick = onClick),

        contentAlignment = Alignment.Center
    ) {

        if (checked) {

            Icon(
                painter = painterResource(Res.drawable.ic_check_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun CircleCheckButtonPreview() {
    ShoppingListTheme {
        CircleCheckButton(
            checked = true,
            onClick = {}
        )
    }
}

@Preview
@PreviewLightDark
@Composable
private fun CircleUncheckButtonPreview() {
    ShoppingListTheme {
        CircleCheckButton(
            checked = false,
            onClick = {}
        )
    }
}
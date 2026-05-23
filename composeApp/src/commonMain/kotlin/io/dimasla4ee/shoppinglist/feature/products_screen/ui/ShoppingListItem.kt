package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.presentation.mappers.toStringResource
import io.dimasla4ee.shoppinglist.core.utils.toFormattedString
import org.jetbrains.compose.resources.stringResource

@Composable
fun ShoppingListItem(
    item: Product,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleCheckButton(
            checked = item.isChecked,
            onClick = onCheckedChange
        )

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (item.isChecked) {
                    TextDecoration.LineThrough
                } else {
                    null
                }
            )

            if (item.amount.isBlank()) return@Column

            val amount = item.amount
            val unit = item.unit

            Text(
                text = "$amount $unit",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun ShoppingListItemPreview() {
    ShoppingListTheme {
        ShoppingListItem(
            item = Product(
                id = 1L,
                listId = 1,
                name = "Колбасевич",
                amount = "1",
                unit = stringResource(MeasurementUnit.KILOGRAM.toStringResource()),
                isChecked = false,
                position = 0
            ),
            onCheckedChange = {}
        )
    }
}

@Preview
@PreviewLightDark
@Composable
private fun ShoppingListItemCheckedPreview() {
    ShoppingListTheme {
        ShoppingListItem(
            item = Product(
                id = 1L,
                listId = 1,
                name = "Хлеб",
                amount = "2",
                unit = stringResource(MeasurementUnit.PIECE.toStringResource()),
                isChecked = true,
                position = 0
            ),
            onCheckedChange = {}
        )
    }
}
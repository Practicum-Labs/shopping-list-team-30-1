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

            if (item.amount == 0f) return@Column

            val amount = item.amount.toFormattedString()
            val unit = stringResource(item.unit.toStringResource())

            Text(
                text = "${item.amount} ${
                    item.unit?.let { unit ->
                        stringResource(unit.toStringResource())
                    } ?: ""
                }",

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
                amount = 1f,
                unit = MeasurementUnit.KILOGRAM,
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
                amount = 2f,
                unit = MeasurementUnit.PIECE,
                isChecked = true,
                position = 0
            ),
            onCheckedChange = {}
        )
    }
}
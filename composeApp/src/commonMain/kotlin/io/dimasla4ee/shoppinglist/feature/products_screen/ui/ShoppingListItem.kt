package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.model.ProductItem
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.model.UnitType
import org.jetbrains.compose.resources.stringResource

@Composable
fun ShoppingListItem(
    item: ProductItem,
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

        RadioButton(
            selected = item.isChecked,
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

            Text(
                text = "${item.count} ${
                    stringResource(item.unit.titleRes)
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
            item = ProductItem(
                id = 1L,
                name = "Колбасевич",
                count = "3",
                unit = UnitType.KILOGRAM,
                isChecked = false
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
            item = ProductItem(
                id = 1L,
                name = "Хлеб",
                count = "2",
                unit = UnitType.PIECE,
                isChecked = true
            ),

            onCheckedChange = {}
        )
    }
}
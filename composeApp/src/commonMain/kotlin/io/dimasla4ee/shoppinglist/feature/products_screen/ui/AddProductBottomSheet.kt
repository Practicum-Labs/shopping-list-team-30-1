package io.dimasla4ee.shoppinglist.feature.products_screen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.model.UnitType
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.content_minus
import shoppinglist.composeapp.generated.resources.hint_item
import shoppinglist.composeapp.generated.resources.hint_new_item
import shoppinglist.composeapp.generated.resources.hint_quantity
import shoppinglist.composeapp.generated.resources.ic_minus_24
import shoppinglist.composeapp.generated.resources.ic_plus_24


private const val HALF_WEIGHT = 0.5f

@Composable
fun AddProductBottomSheet(
    name: String,
    unit: UnitType,
    count: String,
    onNameChange: (String) -> Unit,
    onCountChange: (String) -> Unit,
    onUnitChange: (UnitType) -> Unit,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            textStyle = MaterialTheme.typography.bodyLarge,

            colors = shoppingListTextFieldColors(),

            label = { Text(stringResource(Res.string.hint_item)) },
            placeholder = { Text(stringResource(Res.string.hint_new_item)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Количество
            OutlinedTextField(
                value = count,
                onValueChange = { value ->

                    if (value.all { it.isDigit() }) {
                        onCountChange(value)
                    }
                },

                textStyle = MaterialTheme.typography.bodyLarge,
                colors = shoppingListTextFieldColors(),
                label = {
                    Text(
                        text = stringResource(Res.string.hint_quantity),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                modifier = Modifier.weight(HALF_WEIGHT)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Единицы
            UnitDropdownField(
                selectedUnit = unit,
                onUnitSelect = onUnitChange,
                modifier = Modifier.weight(HALF_WEIGHT),
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Кнопки
            CounterIconButton(
                icon = Res.drawable.ic_minus_24,
                contentDescription = stringResource(Res.string.content_minus),
                onClick = onDecreaseClick,
                enabled = (count.toIntOrNull() ?: 0) > 0
            )

            Spacer(modifier = Modifier.width(4.dp))

            CounterIconButton(
                icon = Res.drawable.ic_plus_24,
                contentDescription = stringResource(Res.string.content_minus),
                onClick = onIncreaseClick
            )
        }
    }
}

@Composable
fun shoppingListTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,

    focusedBorderColor = MaterialTheme.colorScheme.secondary,
    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,

    focusedLabelColor = MaterialTheme.colorScheme.secondary,
    unfocusedLabelColor = MaterialTheme.colorScheme.secondary,

    focusedTextColor = MaterialTheme.colorScheme.onSecondary,
    unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,

    cursorColor = MaterialTheme.colorScheme.secondary
)

@Preview(showBackground = true)
@Composable
private fun AddProductBottomSheetPreview() {
    ShoppingListTheme {
        AddProductBottomSheet(
            name = "",
            count = "",
            unit = UnitType.PIECE,
            onNameChange = {},
            onCountChange = {},
            onUnitChange = {},
            onIncreaseClick = {},
            onDecreaseClick = {},
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF19120C
)
@Composable
private fun AddProductBottomSheetDarkPreview() {
    ShoppingListTheme(darkTheme = true) {
        AddProductBottomSheet(
            name = "",
            count = "",
            unit = UnitType.PIECE,
            onNameChange = {},
            onCountChange = {},
            onUnitChange = {},
            onIncreaseClick = {},
            onDecreaseClick = {},
        )
    }
}
package io.dimasla4ee.shoppinglist.feature.products_screen.ui.bottom_sheets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.presentation.mappers.toStringResource
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.hint_units

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitDropdownField(
    selectedUnit: String,
    onUnitSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf(selectedUnit) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = textValue,
            onValueChange = { newText ->
                textValue = newText
                onUnitSelect(textValue)
            },
            label = {
                Text(
                    text = stringResource(Res.string.hint_units),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            colors = shoppingListTextFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            MeasurementUnit.entries.forEach { currentUnit ->
                val textUnit = stringResource(currentUnit.toStringResource())
                DropdownMenuItem(
                    text = { Text(textUnit) },
                    onClick = {
                        textValue = textUnit
                        onUnitSelect(textValue)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UnitDropdownFieldPreview() {
    ShoppingListTheme {
        UnitDropdownField(
            selectedUnit = stringResource(MeasurementUnit.LITER.toStringResource()),
            onUnitSelect = {}
        )
    }
}
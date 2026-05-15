package io.dimasla4ee.shoppinglist.feature.products_screen.ui

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
import androidx.compose.ui.tooling.preview.Preview
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.model.UnitType
import org.jetbrains.compose.resources.stringResource
import shoppinglist.composeapp.generated.resources.Res
import shoppinglist.composeapp.generated.resources.hint_units

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitDropdownField(
    selectedUnit: UnitType,
    onUnitSelect: (UnitType) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {

        OutlinedTextField(
            value = stringResource(selectedUnit.titleRes),
            onValueChange = {},
            readOnly = true,
            label = {
                Text(stringResource(Res.string.hint_units))
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

            UnitType.entries.forEach { currentUnit ->

                DropdownMenuItem(
                    text = {
                        Text(stringResource(currentUnit.titleRes))
                    },

                    onClick = {
                        onUnitSelect(currentUnit)
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
            selectedUnit = UnitType.LITER,
            onUnitSelect = {}
        )
    }
}
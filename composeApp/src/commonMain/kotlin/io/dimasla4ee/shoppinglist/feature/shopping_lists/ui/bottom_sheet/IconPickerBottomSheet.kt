package io.dimasla4ee.shoppinglist.feature.shopping_lists.ui.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import io.dimasla4ee.shoppinglist.app.ui.theme.AppDimensions
import io.dimasla4ee.shoppinglist.app.ui.theme.ShoppingListTheme
import io.dimasla4ee.shoppinglist.core.domain.model.ShoppingListIcon
import io.dimasla4ee.shoppinglist.core.presentation.mappers.toDrawableResource
import org.jetbrains.compose.resources.painterResource

private const val ICON_PICKER_COLUMNS = 5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconPickerBottomSheet(
    selectedIcon: ShoppingListIcon?,
    onIconSelect: (ShoppingListIcon) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = modifier.padding(horizontal = AppDimensions.paddingVerySmall),
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        dragHandle = null,
        shape = AppDimensions.BottomSheet.topCornerRadius
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppDimensions.paddingMedium, bottom = AppDimensions.paddingVeryBig),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier.size(AppDimensions.BottomSheet.handlerSize),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.outlineVariant
            ) {}
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(ICON_PICKER_COLUMNS),

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppDimensions.paddingMedium,
                    vertical = AppDimensions.paddingMedium
                ),

            horizontalArrangement = Arrangement.spacedBy(
                space = AppDimensions.bottomSheetIconsSpaceBy,
                alignment = Alignment.CenterHorizontally
            ),

            verticalArrangement = Arrangement.spacedBy(AppDimensions.bottomSheetIconsSpaceBy),
            contentPadding = PaddingValues(horizontal = AppDimensions.bottomSheetIconsSpaceBy)
        ) {

            items(ShoppingListIcon.entries) { icon ->
                val isSelected = icon == selectedIcon

                Box(
                    modifier = Modifier.size(AppDimensions.clickableAreaOfIcon),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier.size(AppDimensions.areaOfIcon),
                        shape = CircleShape,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.tertiaryContainer
                        }
                    ) {}

                    IconButton(
                        modifier = Modifier.size(AppDimensions.clickableAreaOfIcon),
                        onClick = {
                            onIconSelect(icon)
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                icon.toDrawableResource()
                            ),
                            contentDescription = icon.name,
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            } else {
                                MaterialTheme.colorScheme.surfaceContainerHighest
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@PreviewLightDark
@Composable
private fun IconPickerBottomSheetPreview() {

    ShoppingListTheme {
        IconPickerBottomSheet(
            selectedIcon = ShoppingListIcon.SHOPPING_CART,
            onIconSelect = {},
            onDismiss = {}
        )
    }
}
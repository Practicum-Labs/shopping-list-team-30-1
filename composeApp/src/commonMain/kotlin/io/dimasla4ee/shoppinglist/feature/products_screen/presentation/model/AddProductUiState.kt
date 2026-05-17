package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product

data class AddProductUiState(
    val name: String = "",
    val amount: String = "",
    val unit: MeasurementUnit = MeasurementUnit.PIECE,
    val items: List<Product> = emptyList(),

    val isBottomSheetOpen: Boolean = false
)

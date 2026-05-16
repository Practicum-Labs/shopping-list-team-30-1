package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.feature.products_screen.domain.model.ProductItem
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.model.UnitType

data class AddProductUiState(
    val name: String = "",
    val count: String = "",
    val unit: UnitType = UnitType.PIECE,
    val items: List<ProductItem> = emptyList(),

    val isBottomSheetOpen: Boolean = false
)

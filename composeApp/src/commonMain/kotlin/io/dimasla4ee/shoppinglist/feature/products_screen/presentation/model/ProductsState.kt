package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.mvi.MviState
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode
import io.dimasla4ee.shoppinglist.feature.products_screen.presentation.state.ProductDialog

data class ProductsState(
    val name: String = "",
    val amount: String = "",
    val unit: MeasurementUnit? = null,
    val items: List<Product> = emptyList(),
    val sortMode: SortMode = SortMode.CUSTOM,
    val isBottomSheetOpen: Boolean = false,
    val dialog: ProductDialog = ProductDialog.None,
    val isMenuBottomSheetOpen: Boolean = false,
) : MviState {
    val sortedItems: List<Product>
        get() = when (sortMode) {
            SortMode.CUSTOM -> items.sortedBy { it.position }
            SortMode.ALPHABETICAL -> items.sortedBy { it.name.lowercase() }
        }
}
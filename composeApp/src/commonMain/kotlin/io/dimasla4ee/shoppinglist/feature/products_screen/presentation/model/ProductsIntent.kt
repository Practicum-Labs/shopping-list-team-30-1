package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.mvi.MviIntent
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode

sealed interface ProductsIntent : MviIntent {

    data class ChangeName(val name: String) : ProductsIntent
    data class ChangeCount(val amount: String) : ProductsIntent
    data class ChangeUnit(val unit: MeasurementUnit) : ProductsIntent

    data object IncreaseCount : ProductsIntent
    data object DecreaseCount : ProductsIntent
    data object AddItem : ProductsIntent

    data object ToggleBottomSheet : ProductsIntent
    data class ToggleItemChecked(val product: Product) : ProductsIntent

    data class ReorderProduct(
        val fromIndex: Int,
        val toIndex: Int
    ) : ProductsIntent
    data object CommitReorder : ProductsIntent

    data object ToggleSortMode : ProductsIntent

    data object ShowDeleteAllDialog : ProductsIntent

    data object ShowDeleteCheckedDialog : ProductsIntent

    data object DismissDialog : ProductsIntent

    data object ConfirmDeleteAll : ProductsIntent

    data object ConfirmDeleteChecked : ProductsIntent

    data object ToggleMenuBottomSheet : ProductsIntent
    data object DeleteAllProducts : ProductsIntent
    data object DeleteCheckedProducts : ProductsIntent

    data class ChangeSortMode(val mode: SortMode) : ProductsIntent
    data class EditProduct(val product: Product) : ProductsIntent
}
package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.mvi.MviIntent

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

    data object ToggleSortMode : ProductsIntent
}
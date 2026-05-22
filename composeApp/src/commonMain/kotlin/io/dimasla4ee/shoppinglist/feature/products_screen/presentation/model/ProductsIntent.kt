package io.dimasla4ee.shoppinglist.feature.products_screen.presentation.model

import io.dimasla4ee.shoppinglist.core.domain.model.MeasurementUnit
import io.dimasla4ee.shoppinglist.core.domain.model.Product
import io.dimasla4ee.shoppinglist.core.mvi.MviIntent
import io.dimasla4ee.shoppinglist.feature.products_screen.domain.SortMode

sealed interface ProductsIntent : MviIntent {
    sealed interface Action : ProductsIntent {
        data object AddItem : Action
        data object DeleteAllProducts : Action
        data object DeleteCheckedProducts : Action
        data object DeleteList : Action
        data object CommitReorder : Action
        data object DeleteProduct : Action
        data class ChangeSortMode(val mode: SortMode) : Action
        data class ToggleItemChecked(val product: Product) : Action
        data object ToggleSortMode : Action
        data object OnBackClick : Action
    }

    sealed interface UI : ProductsIntent {
        data class ChangeName(val name: String) : UI
        data class ChangeCount(val amount: String) : UI
        data class ChangeUnit(val unit: MeasurementUnit) : UI
        data object IncreaseCount : UI
        data object DecreaseCount : UI
        data object ToggleBottomSheet : UI
        data class ReorderProduct(val fromIndex: Int, val toIndex: Int) : UI
        data object ShowDeleteAllDialog : UI
        data object ShowDeleteCheckedDialog : UI
        data object ShowDeleteListDialog : UI
        data object DismissDialog : UI
        data object ToggleMenuBottomSheet : UI
        data class EditProduct(val product: Product) : UI
    }
}
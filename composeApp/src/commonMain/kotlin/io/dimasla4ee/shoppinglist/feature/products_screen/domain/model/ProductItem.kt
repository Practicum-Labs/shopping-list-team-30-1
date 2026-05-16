package io.dimasla4ee.shoppinglist.feature.products_screen.domain.model

data class ProductItem(
    val id: Long,
    val name: String,
    val count: String,
    val unit: UnitType,
    val isChecked: Boolean = false
)

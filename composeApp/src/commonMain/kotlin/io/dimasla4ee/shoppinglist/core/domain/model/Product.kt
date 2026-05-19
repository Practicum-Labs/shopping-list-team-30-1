package io.dimasla4ee.shoppinglist.core.domain.model

data class Product(
    val id: Long,
    val name: String,
    val amount: String,
    val unit: MeasurementUnit,
    val position: Int,
    val isChecked: Boolean = false
)

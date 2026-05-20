package io.dimasla4ee.shoppinglist.core.domain.model

data class Product(
    val id: Long,
    val listId: Long,
    val name: String,
    val amount: Float,
    val unit: MeasurementUnit?,
    val position: Int,
    val isChecked: Boolean = false
)

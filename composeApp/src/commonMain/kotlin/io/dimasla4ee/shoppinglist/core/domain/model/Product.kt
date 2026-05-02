package io.dimasla4ee.shoppinglist.core.domain.model

data class Product(
    val name: String,
    val amount: Float,
    val unit: MeasurementUnit
)

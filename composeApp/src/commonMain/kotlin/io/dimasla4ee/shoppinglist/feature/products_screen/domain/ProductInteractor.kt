package io.dimasla4ee.shoppinglist.feature.products_screen.domain

import io.dimasla4ee.shoppinglist.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductInteractor {

    fun getProductsOfList(listId: Long): Flow<List<Product>>

    suspend fun addProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun deleteCheckedProducts(listId: Long)

    suspend fun deleteAllProducts(listId: Long)
}
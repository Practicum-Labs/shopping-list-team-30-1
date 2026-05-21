package io.dimasla4ee.shoppinglist.feature.products_screen.domain

import io.dimasla4ee.shoppinglist.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

class ProductInteractorImpl(
    private val repository: ProductRepository) : ProductInteractor {

    override fun getProductsOfList(listId: Long): Flow<List<Product>> {
        return repository.getProductsOfList(listId)
    }

    override suspend fun addProduct(product: Product) {
        repository.addProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        repository.updateProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        repository.deleteProduct(product)
    }

    override suspend fun deleteCheckedProducts(listId: Long) {
        repository.deleteCheckedProducts(listId)
    }

    override suspend fun deleteAllProducts(listId: Long) {
        repository.deleteAllProducts(listId)
    }
}
package com.example.myapplication.data.repository

import com.example.myapplication.data.api.ProductApi
import com.example.myapplication.data.api.response.mapper.toProductEntity
import com.example.myapplication.data.db.ProductDao
import com.example.myapplication.data.db.toProductInformation
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.domain.ProductInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val preferenceManager: PreferenceManager,
    private val productDao: ProductDao,
    private val dispatcher: CoroutineDispatcher
) : ProductRepository {

    override val products: Flow<List<ProductInformation>> =
        productDao.getProductList()
            .distinctUntilChanged()
            .map { it.toProductInformation().sortedByDescending { it.isProductInterested } }
            .flowOn(dispatcher)

    override suspend fun refreshProducts(): Unit = withContext(dispatcher) {
        productApi.getProductList(getCurrentUser()!!)
            .body()
            .also { response ->
                if (response != null) {
                    response.data?.productList!!.toProductEntity()
                        .let { productDao.insertProductList(it) }
                }
            }
    }

    override suspend fun updateProductFavorite(productId: Int) {
        val productItem = productDao.getProductItem(productId)
        productDao.updateProduct(productItem.copy(isProductInterested = !productItem.isProductInterested!!))
    }

    override suspend fun updateRemoteProductList() {
        productApi.getProductList(getCurrentUser()!!)
            .body()
            ?.data
            ?.productList
            ?.map { productItem ->
                val productId = productItem.productId!!
                val productEntity = productDao.getProductItem(productId)
                val userId = getCurrentUser()!!
                if (productEntity.isProductInterested != productItem.isProductInterested) {
                    if (productEntity.isProductInterested == true) {
                        productApi.addProductItemToWishList(userId, productId)
                    } else {
                        productApi.removeProductItemFromWishList(userId, productId)
                    }
                }
            }
    }

    private fun getCurrentUser(): String? {
        return preferenceManager.getUserId()
    }
}
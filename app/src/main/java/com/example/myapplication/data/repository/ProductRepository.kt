package com.example.myapplication.data.repository

import com.example.myapplication.domain.ProductInformation
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val products: Flow<List<ProductInformation>>

    suspend fun refreshProducts()

    suspend fun updateProductFavorite(productId: Int)

    suspend fun updateRemoteProductList()
}
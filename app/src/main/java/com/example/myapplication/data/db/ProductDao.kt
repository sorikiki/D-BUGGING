package com.example.myapplication.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity WHERE productId=:productId")
    suspend fun getProductItem(productId: Int): ProductEntity

    @Query("SELECT * FROM ProductEntity")
    fun getProductList(): Flow<List<ProductEntity>>

    @Query("DELETE FROM ProductEntity")
    fun removeProductItems()

    @Update
    suspend fun updateProduct(productEntity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(products: List<ProductEntity>)
}
package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.ProductItemResponse
import com.example.myapplication.data.api.response.ProductListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("/product")
    suspend fun getProductList(): Call<ProductListResponse>

    @GET("/product/{product_id}/item")
    suspend fun getProductItem(
        @Path("product_id") productId: Int
    ): Call<ProductItemResponse>
}
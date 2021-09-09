package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.ProductItemResponse
import com.example.myapplication.data.api.response.ProductListResponse
import retrofit2.Call
import retrofit2.http.*

interface ProductApi {
    // 제품 목록 요청
    @GET("/product")
    suspend fun getProductList(): Call<ProductListResponse>

    // 제품 상세 정보 요청
    @GET("/product/{product_id}/item")
    suspend fun getProductItem(
        @Path("product_id") productId: Int
    ): Call<ProductItemResponse>

    // 제품 찜하기 추가
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @POST("/product/interest")
    suspend fun addProductItemToWishList(
        @Field("user_id") userId: String,
        @Field("product_id") productId: Int
    )

    // 제품 찜하기 해제
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @DELETE("/product/interest")
    suspend fun removeProductItemFromWishList(
        @Field("user_id") userId: String,
        @Field("product_id") productId: Int
    )
}
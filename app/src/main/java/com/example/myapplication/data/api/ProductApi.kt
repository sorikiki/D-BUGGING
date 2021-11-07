package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.ProductListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {
    // 제품 목록 요청
    @GET("/product/{user_id}")
    suspend fun getProductList(
        @Path("user_id") userId: String
    ): Response<ProductListResponse>

    // 제품 찜하기 추가
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/product/interest/insertion")
    suspend fun addProductItemToWishList(
        @Field("user_id") userId: String,
        @Field("product_id") productId: Int
    )

    // 제품 찜하기 해제
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/product/interest/removal")
    suspend fun removeProductItemFromWishList(
        @Field("user_id") userId: String,
        @Field("product_id") productId: Int
    )
}
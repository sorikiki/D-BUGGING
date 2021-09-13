package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class MyPageProductDetailResponse(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("status")
    val status: Int? = null,

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("product_list")
    val productList: List<ProductItem>? = null
)
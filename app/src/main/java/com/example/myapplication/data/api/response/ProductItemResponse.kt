package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ProductItemResponse(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("status")
    val status: Int? = null,

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("data")
    val productList: ProductItem? = null
)

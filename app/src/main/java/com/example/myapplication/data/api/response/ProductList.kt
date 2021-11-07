package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("product_list")
    val productList: List<ProductItem>?
)

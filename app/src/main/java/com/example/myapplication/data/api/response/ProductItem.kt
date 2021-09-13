package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("product_id")
    val productId: Int? = null,

    @SerializedName("name")
    val productName: String? = null,

    @SerializedName("type")
    val productType: Int? = null,

    @SerializedName("short_intro")
    val shortIntro: String? = null,

    @SerializedName("description")
    val productDescription: String? = null,

    @SerializedName("tag")
    val tag: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null,

    @SerializedName("interest_num")
    val interestNum: Int? = null
)

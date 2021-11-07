package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("product_id")
    val productId: Int? = null,

    @SerializedName("product_name")
    val productName: String? = null,

    @SerializedName("product_type")
    val productType: String? = null,

    @SerializedName("product_short_intro")
    val shortIntro: String? = null,

    @SerializedName("product_description")
    val productDescription: String? = null,

    @SerializedName("tag")
    val tag: String? = null,

    @SerializedName("thumbnail_image")
    val thumbnail: String? = null,

    @SerializedName("interest_num")
    val interestNum: Int? = null,

    @SerializedName("is_product_interested")
    val isProductInterested: Boolean? = false
)

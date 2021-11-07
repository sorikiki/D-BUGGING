package com.example.myapplication.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInformation(
    val productId: Int? = null,
    val productName: String? = null,
    val productType: String? = null,
    val shortIntro: String? = null,
    val productDescription: String? = null,
    val tag: String? = null,
    val thumbnail: String? = null,
    val interestNum: Int? = null,
    val isProductInterested: Boolean? = false
) : Parcelable


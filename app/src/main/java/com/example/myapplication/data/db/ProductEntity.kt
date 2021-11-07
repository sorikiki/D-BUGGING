package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.ProductInformation

@Entity
data class ProductEntity(
    @PrimaryKey val productId: Int? = null,
    val productName: String? = null,
    val productType: String? = null,
    val shortIntro: String? = null,
    val productDescription: String? = null,
    val tag: String? = null,
    val thumbnail: String? = null,
    val interestNum: Int? = null,
    var isProductInterested: Boolean? = false
)

fun ProductEntity.toProductInformation(): ProductInformation =
    ProductInformation(
        productId,
        productName,
        productType,
        shortIntro,
        productDescription,
        tag,
        thumbnail,
        interestNum,
        isProductInterested
    )

fun List<ProductEntity>.toProductInformation(): List<ProductInformation> =
    map { it.toProductInformation() }
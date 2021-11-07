package com.example.myapplication.data.api.response.mapper

import com.example.myapplication.data.api.response.ProductItem
import com.example.myapplication.data.db.ProductEntity
import com.example.myapplication.domain.ProductInformation

fun ProductItem.toProductInformation(): ProductInformation =
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

fun List<ProductItem>.toProductInformation(): List<ProductInformation> =
    map { it.toProductInformation() }

fun ProductItem.toProductEntity(): ProductEntity =
    ProductEntity(
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

fun List<ProductItem>.toProductEntity(): List<ProductEntity> =
    map { it.toProductEntity() }
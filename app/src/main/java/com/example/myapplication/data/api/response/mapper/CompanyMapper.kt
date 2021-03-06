package com.example.myapplication.data.api.response.mapper

import com.example.myapplication.data.api.response.CompanyItem
import com.example.myapplication.data.db.CompanyEntity
import com.example.myapplication.domain.CompanyInformation

fun CompanyItem.toCompanyInformation(): CompanyInformation =
    CompanyInformation(
        companyId,
        companyName,
        shortIntro,
        description,
        contactNumber,
        killableBugs,
        availableArea,
        availableCounselTime,
        thumbNail,
        isCompanyInterested
    )

fun List<CompanyItem>.toCompanyInformation(): List<CompanyInformation> =
    map { it.toCompanyInformation() }

fun CompanyItem.toCompanyEntity(): CompanyEntity =
    CompanyEntity(
        companyId,
        companyName,
        shortIntro,
        description,
        contactNumber,
        killableBugs,
        availableArea,
        availableCounselTime,
        thumbNail,
        isCompanyInterested
    )

fun List<CompanyItem>.toCompanyEntity(): List<CompanyEntity> =
    map { it.toCompanyEntity() }
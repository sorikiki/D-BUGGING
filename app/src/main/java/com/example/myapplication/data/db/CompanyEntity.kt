package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.api.response.CompanyItem
import com.example.myapplication.data.api.response.mapper.toCompanyInformation
import com.example.myapplication.domain.CompanyInformation

@Entity
data class CompanyEntity(
    @PrimaryKey val companyId: Int? = null,
    val companyName: String? = null,
    val shortIntro: String? = null,
    val description: String? = null,
    val contactNumber: String? = null,
    val killableBugs: String? = null,
    val availableArea: String? = null,
    val availableCounselTime: String? = null,
    val thumbNail: String? = null,
    var isCompanyInterested: Boolean? = false
)

fun CompanyEntity.toCompanyInformation(): CompanyInformation =
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

fun List<CompanyEntity>.toCompanyInformation(): List<CompanyInformation> =
    map { it.toCompanyInformation() }
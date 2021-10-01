package com.example.myapplication.domain

data class CompanyInformation (
    val companyId: Int? = null,
    val companyName: String? = null,
    val shortIntro: String? = null,
    val description: String? = null,
    val contactNumber: String? = null,
    val killableBugs: String? = null,
    val availableArea: String? = null,
    val availableCounselTime: String? = null,
    val thumbNail: String? = null,
    val isCompanyInterested: Boolean? = false
)
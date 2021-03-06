package com.example.myapplication.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
    var isCompanyInterested: Boolean? = false
) : Parcelable
package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class CompanyItem(
    @SerializedName("company_id")
    val companyId: Int? = null,

    @SerializedName("company_name")
    val companyName: String? = null,

    @SerializedName("company_short_intro")
    val shortIntro: String? = null,

    @SerializedName("company_description")
    val description: String? = null,

    @SerializedName("contact_numbers")
    val contactNumber: String? = null,

    @SerializedName("killable_bugs")
    val killableBugs: String? = null,

    @SerializedName("available_areas")
    val availableArea: String? = null,

    @SerializedName("available_counsel_time")
    val availableCounselTime: String? = null,

    @SerializedName("thumbnail_image")
    val thumbNail: String? = null,

    @SerializedName("is_company_interested")
    val isCompanyInterested: Boolean? = false
)

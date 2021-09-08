package com.example.myapplication.data.api

import com.google.gson.annotations.SerializedName

data class ReservationInfo(
    @SerializedName("company_id")
    val companyId: Int,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("type")
    val bugName: String,

    @SerializedName("found_date")
    val firstFoundDate: String,

    @SerializedName("found_place")
    val firstFoundPlace: String,

    @SerializedName("has_bug_been_shown")
    val hasBugBeenShown: Boolean = false,

    @SerializedName("wanted_start_date")
    val wantedStartDate: String,

    @SerializedName("wanted_end_date")
    val wantedEndDate: String,

    @SerializedName("reserve_date_time")
    val reserveDateTime: String,

    @SerializedName("available_visit_time")
    val availableVisitTime: String,

    @SerializedName("extra_message")
    val extraMessage: String? = null
)

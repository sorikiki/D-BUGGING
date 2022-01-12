package com.example.myapplication.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationInfo(
    @SerializedName("company_id")
    val companyId: Int,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("bug_name")
    val bugName: String,

    @SerializedName("first_found_date")
    val firstFoundDate: String,

    @SerializedName("first_found_place")
    val firstFoundPlace: String,

    @SerializedName("has_bug_been_shown")
    val hasBugBeenShown: Int = 0,

    @SerializedName("wanted_date")
    val wantedDate: String,

    @SerializedName("reserve_date_time")
    val reserveDateTime: String,

    @SerializedName("wanted_time")
    val availableVisitTime: String,

    @SerializedName("extra_message")
    val extraMessage: String? = null
): Parcelable

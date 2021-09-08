package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ReservationDetail(
    @SerializedName("company_name")
    val companyName: String? = null,

    @SerializedName("wanted_date")
    val wantedDate: String? = null,

    @SerializedName("wanted_time")
    val wantedTime: String? = null,

    @SerializedName("user_contact_num")
    val userContactNumber: String? = null,

    @SerializedName("user_address")
    val userAddress: String? = null,

    @SerializedName("bug_type")
    val bugType: String? = null,

    @SerializedName("found_date")
    val firstFoundDate: String? = null,

    @SerializedName("found_place")
    val firstFoundPlace: String? = null,

    @SerializedName("num_of_rooms")
    val numOfRooms: Int? = null,

    @SerializedName("has_bug_been_shown")
    val hasBugBeenShown: Boolean? = false,

    @SerializedName("extra_message")
    val extraMessage: String? = null
)

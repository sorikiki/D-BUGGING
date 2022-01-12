package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ReservationCheck(
    @SerializedName("wanted_date")
    val wantedDate: String? = null,

    @SerializedName("wanted_time")
    val wantedTime: String? = null,

    @SerializedName("bug_name")
    val bugType: String? = null,

    @SerializedName("first_found_date")
    val firstFoundDate: String? = null,

    @SerializedName("first_found_place")
    val firstFoundPlace: String? = null,

    @SerializedName("has_bug_been_shown")
    val hasBugBeenShown: Int? = 0,

    @SerializedName("extra_message")
    val extraMessage: String? = null,

    @SerializedName("process_state")
    val processState: Int? = 1,

    @SerializedName("reserve_date_time")
    val reserveDateTime: String? = null,

    @SerializedName("visit_date_time")
    val visitDateTime: String? = null,

    @SerializedName("engineer_name")
    val engineerName: String? = null,

    @SerializedName("engineer_num")
    val engineerContactNumber: String? = null,

    @SerializedName("expected_estimate")
    val expectedEstimate: Int? = null
)

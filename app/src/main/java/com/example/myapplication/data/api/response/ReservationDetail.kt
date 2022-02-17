package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ReservationDetail(
    @SerializedName("reservation_id")
    val reservationId: Int? = null,

    @SerializedName("user_id")
    var userId: String? = null,

    @SerializedName("company_id")
    var companyId: Int? = null,

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
    val processState: Int? = 0,

    @SerializedName("reserve_date_time")
    val reserveDateTime: String? = null,

    @SerializedName("visit_date_time")
    val visitDateTime: String? = null,

    @SerializedName("engineer_name")
    val engineerName: String? = null,

    @SerializedName("engineer_num")
    val engineerContactNumber: String? = null,

    @SerializedName("expected_estimate")
    val expectedEstimate: Int? = null,

    @SerializedName("company_name")
    val companyName: String? = null,

    @SerializedName("confirm_date_time")
    val confirmDateTime: String? = null,

    @SerializedName("reserve_completed_date_time")
    val reserveCompletedDateTime: String? = null,
)

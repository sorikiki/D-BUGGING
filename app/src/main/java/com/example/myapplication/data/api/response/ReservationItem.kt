package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class ReservationItem(
    @SerializedName("reservation_id")
    val reservationId: Int? = null,

    @SerializedName("process_state")
    val processState: Int? = null,

    @SerializedName("company_name")
    val companyName: String? = null,

    @SerializedName("bug_name")
    val bugName: String? = null,

    @SerializedName("reserve_date_time")
    val reserveDateTime: String? = null,

    @SerializedName("visit_date_time")
    val visitDateTime: String? = null
)

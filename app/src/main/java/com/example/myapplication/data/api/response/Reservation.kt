package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class Reservation(
    @SerializedName("reservation_id")
    val reservationId: Int? = null
)

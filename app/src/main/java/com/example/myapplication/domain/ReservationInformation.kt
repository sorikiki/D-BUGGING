package com.example.myapplication.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationInformation(
    val reservationId: Int? = null,
    val userId: String? = null,
    val companyId: Int? = null,
    val companyName: String? = null,
    val wantedDate: String? = null,
    val wantedTime: String? = null,
    val bugType: String? = null,
    val firstFoundDate: String? = null,
    val firstFoundPlace: String? = null,
    val hasBugBeenShown: Int? = 0,
    val extraMessage: String? = null,
    val processState: Int? = 0,
    val reserveDateTime: String? = null,
    val visitDateTime: String? = null,
    val engineerName: String? = null,
    val engineerContactNumber: String? = null,
    val expectedEstimate: Int? = null,
    val confirmDateTime: String? = null,
    val reserveCompletedDateTime: String? = null
) : Parcelable

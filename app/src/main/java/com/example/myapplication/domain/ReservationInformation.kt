package com.example.myapplication.domain

data class ReservationInformation(
    val userName: String? = null,
    val companyName: String? = null,
    val wantedDate: String? = null,
    val wantedTime: String? = null,
    val userContactNumber: String? = null,
    val userAddress: String? = null,
    val bugType: String? = null,
    val firstFoundDate: String? = null,
    val firstFoundPlace: String? = null,
    val numOfRooms: Int? = null,
    val hasBugBeenShown: Int? = 0,
    val extraMessage: String? = null,
    val processState: Int? = 1,
    val reserveDateTime: String? = null,
    val visitDateTime: String? = null,
    val engineerName: String? = null,
    val engineerContactNumber: String? = null,
    val expectedEstimate: Int? = null
)

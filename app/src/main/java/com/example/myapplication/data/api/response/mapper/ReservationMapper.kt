package com.example.myapplication.data.api.response.mapper

import com.example.myapplication.data.api.response.ReservationDetail
import com.example.myapplication.domain.ReservationInformation

fun ReservationDetail.toReservationInformation(): ReservationInformation =
    ReservationInformation(
        reservationId = reservationId,
        wantedDate = wantedDate,
        wantedTime = wantedTime,
        bugType = bugType,
        firstFoundDate = firstFoundDate,
        firstFoundPlace = firstFoundPlace,
        hasBugBeenShown = hasBugBeenShown,
        extraMessage = extraMessage,
        processState = processState,
        reserveDateTime = reserveDateTime,
        visitDateTime = visitDateTime,
        engineerName = engineerName,
        engineerContactNumber = engineerContactNumber,
        expectedEstimate = expectedEstimate,
        companyName = companyName,
        userId = userId,
        companyId = companyId,
        confirmDateTime = confirmDateTime,
        reserveCompletedDateTime = reserveCompletedDateTime
    )

fun List<ReservationDetail>.toReservationInformation(): List<ReservationInformation> =
    map { it.toReservationInformation() }

package com.example.myapplication.data.api.response.mapper

import com.example.myapplication.data.api.response.ReservationCheck
import com.example.myapplication.domain.ReservationInformation

fun ReservationCheck.toReservationInformation(): ReservationInformation =
    ReservationInformation(
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
        expectedEstimate = expectedEstimate
    )
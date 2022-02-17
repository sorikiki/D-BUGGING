package com.example.myapplication.ext

import com.example.myapplication.domain.ReservationInformation
import com.example.myapplication.domain.UserInformation

fun ReservationInformation.convertProcessStateToString(): String {
    return when (processState) {
        0 -> "예약 접수"
        1 -> "업체 확인중"
        2 -> "방문예약 접수완료"
        3 -> "케어 종료"
        else -> "상태 확인 불가"
    }
}
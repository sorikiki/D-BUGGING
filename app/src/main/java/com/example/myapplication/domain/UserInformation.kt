package com.example.myapplication.domain

import android.os.Parcelable
import com.example.myapplication.data.api.response.ProductItem
import com.example.myapplication.data.api.response.ReservationItem
import com.example.myapplication.data.api.response.SurveyItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UserInformation(
    val userId: String? = null,
    val userName: String? = null,
    val contactNumber: String? = null,
    val accumulatedNumOfUsages: Int? = null,
    val sizeOfHouse: Double? = null,
    val numOfRooms: Int? = null,
    val roadAddress: String? = null,
    val detailAddress: String? = null,
    val numOfInterestedCompanies: Int? = null,
    val surveyList: List<@RawValue SurveyItem>? = null,
    val productList: List<@RawValue ProductItem>?= null,
    val companyList: List<@RawValue ReservationItem>?= null
): Parcelable

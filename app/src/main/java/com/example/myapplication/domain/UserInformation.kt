package com.example.myapplication.domain

import com.example.myapplication.data.api.response.ProductItem
import com.example.myapplication.data.api.response.ReservationItem
import com.example.myapplication.data.api.response.SurveyItem

data class UserInformation(
    val userId: String? = null,
    val accumulatedNumOfUsages: Int? = null,
    val sizeOfHouse: Double? = null,
    val numOfRooms: Int? = null,
    val numOfInterestedCompanies: Int? = null,
    val surveyList: List<SurveyItem>? = null,
    val productList: List<ProductItem>?= null,
    val companyList: List<ReservationItem>?= null
)

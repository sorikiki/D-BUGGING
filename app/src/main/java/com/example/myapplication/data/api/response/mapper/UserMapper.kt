package com.example.myapplication.data.api.response.mapper

import com.example.myapplication.data.api.response.UserDetail
import com.example.myapplication.domain.UserInformation

fun UserDetail.toUserInformation() =
    UserInformation(
        userId,
        accumulatedNumOfUsages,
        sizeOfHouse,
        numOfRooms,
        numOfInterestedCompanies,
        surveyList,
        productList,
        companyList
)
package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("accumulated_num_of_usages")
    val accumulatedNumOfUsages: Int? = null,

    @SerializedName("size_of_house")
    val sizeOfHouse: Double? = null,

    @SerializedName("num_of_interested_companies")
    val numOfInterestedCompanies: Int? = null,

    @SerializedName("survey_list")
    val surveyList: List<SurveyItem>? = null,

    @SerializedName("product_list")
    val productList: List<ProductItem>?= null,

    @SerializedName("company_list")
    val companyList: List<ReservationItem>?= null
)

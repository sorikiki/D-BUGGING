package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("user_id")
    val userId: String? = null,

    @SerializedName("user_name")
    val userName: String? = null,

    @SerializedName("contact_numbers")
    val contactNumber: String? = null,

    @SerializedName("accumulated_num_of_usages")
    val accumulatedNumOfUsages: Int? = null,

    @SerializedName("size_of_house")
    val sizeOfHouse: Double? = null,

    @SerializedName("num_of_rooms")
    val numOfRooms: Int? = null,

    @SerializedName("road_address")
    val roadAddress: String? = null,

    @SerializedName("detail_address")
    val detailAddress: String? = null,

    @SerializedName("num_of_interested_companies")
    val numOfInterestedCompanies: Int? = null,

    @SerializedName("zip_code")
    val zipCode: Int? = null,

    @SerializedName("survey_list")
    val surveyList: List<SurveyItem>? = null,

    @SerializedName("product_list")
    val productList: List<ProductItem>? = null,

    @SerializedName("reservation_list")
    val reservationList: List<ReservationDetail>? = null
)

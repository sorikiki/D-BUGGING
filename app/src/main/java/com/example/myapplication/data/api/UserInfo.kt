package com.example.myapplication.data.api

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("user_id") val userId: String,
    @SerializedName("user_password") val userPassword: String,
    @SerializedName("contact_numbers") val contactNumbers: String,
    @SerializedName("email_address") val emailAddress: String,
    @SerializedName("road_addr") val roadAddress: String,
    @SerializedName("detail_addr") val detailAddress: String,
    @SerializedName("user_name") val userName: String,
    @SerializedName("accumulated_num_of_usages") val accumulatedNumOfUsages: Int = 0,
    @SerializedName("num_of_rooms") val numOfRooms: Int? = 0,
    @SerializedName("size_of_house") val sizeOfHouse: Double? = 0.0)

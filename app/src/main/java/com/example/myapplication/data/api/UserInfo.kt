package com.example.myapplication.data.api

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id")
    val id: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("contact_numbers")
    val contactNumbers: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("zip_code")
    val zipCode: Int? = null,

    @SerializedName("road_addr")
    val roadAddr: String? = null,

    @SerializedName("detail_addr")
    val detailAddr: String? = null,

    @SerializedName("size_of_house")
    val sizeOfHouse: Double? = null,

    @SerializedName("num_of_rooms")
    val numOfRooms: Int? = null,

    @SerializedName("accumulated_num_of_usages")
    val accumulatedNumOfUsages: Int? = 0
)
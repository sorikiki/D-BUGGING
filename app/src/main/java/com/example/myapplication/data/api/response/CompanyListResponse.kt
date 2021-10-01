package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

class CompanyListResponse {
    @SerializedName("message")
    val message: String? = null

    @SerializedName("status")
    val status: Int? = null

    @SerializedName("success")
    val success: Boolean? = null

    @SerializedName("data")
    val data: CompanyList? = null
}
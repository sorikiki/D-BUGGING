package com.example.myapplication.data.api.response

import com.example.myapplication.domain.BugInformation
import com.google.gson.annotations.SerializedName

class SurveyItemResponse {
    @SerializedName("message")
    val message: String? = null

    @SerializedName("status")
    val status: Int? = null

    @SerializedName("success")
    val success: Boolean? = null

    @SerializedName("data")
    val bugInformation: BugInformation? = null
}
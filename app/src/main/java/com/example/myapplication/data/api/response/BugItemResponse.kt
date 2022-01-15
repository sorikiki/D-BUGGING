package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class BugItemResponse (
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("status")
    val status: Int? = null,

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("data")
    val bugItem: BugItem? = null
)
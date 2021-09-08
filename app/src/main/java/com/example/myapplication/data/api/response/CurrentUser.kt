package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class CurrentUser(
    @SerializedName("current_user_id")
    val userId: String? = null,

    @SerializedName("current_user_name")
    val userName: String? = null
)

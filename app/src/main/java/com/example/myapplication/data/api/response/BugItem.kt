package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class BugItem(
    @SerializedName("bug_id")
    val bugId: Int,

    @SerializedName("bug_name")
    val bugName: String,

    @SerializedName("habitat")
    val habitat: String,

    @SerializedName("movement")
    val movement: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnail")
    val thumbnailUrl: String,

    @SerializedName("actual")
    val actualImageUrl: String
)

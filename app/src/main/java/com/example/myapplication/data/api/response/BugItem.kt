package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class BugItem(
    @SerializedName("bug_id")
    val bugId: Int? = null,

    @SerializedName("bug_name")
    val bugName: String? = null,

    @SerializedName("appearance")
    val appearance: String? = null,

    @SerializedName("color")
    val color: String? = null,

    @SerializedName("habitat")
    val habitat: String? = null,

    @SerializedName("movement")
    val movement: String? = null,

    @SerializedName("survey_result")
    val surveyResult: String? = null
)

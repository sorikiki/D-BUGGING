package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class SurveyItem(
    @SerializedName("survey_id")
    val surveyId: Int? = null,

    @SerializedName("bug_id")
    val bugId: Int? = null,

    @SerializedName("bug_name")
    val bugName: String? = null,

    @SerializedName("survey_date")
    val surveyDate: String? = null
)

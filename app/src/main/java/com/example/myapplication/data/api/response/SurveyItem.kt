package com.example.myapplication.data.api.response

import com.google.gson.annotations.SerializedName

data class SurveyItem(
    @SerializedName("bug_id")
    val bugId: Int? = null,

    @SerializedName("bug_name")
    val bugName: String? = null,

    @SerializedName("survey_date_time")
    val surveyDateTime: String? = null,

    @SerializedName("bug_thumbnail_image")
    val bugThumbnailImage: String? = null
)

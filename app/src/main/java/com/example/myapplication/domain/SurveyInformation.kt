package com.example.myapplication.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurveyInformation(
    val surveyId: Int? = null,
    val bugId: Int? = null,
    val bugName: String? = null,
    val surveyDate: String? = null
): Parcelable

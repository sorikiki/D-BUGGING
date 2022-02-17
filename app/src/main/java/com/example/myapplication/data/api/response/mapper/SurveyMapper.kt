package com.example.myapplication.data.api.response.mapper

import com.example.myapplication.data.api.response.SurveyItem
import com.example.myapplication.domain.SurveyInformation

fun SurveyItem.toSurveyInformation(): SurveyInformation =
    SurveyInformation(
        surveyId,
        bugId,
        bugName,
        surveyDate
    )

fun List<SurveyItem>.toSurveyInformation(): List<SurveyInformation>
    = map { it.toSurveyInformation() }
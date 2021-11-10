package com.example.myapplication.data.api.response.mapper

import com.example.myapplication.data.api.response.BugItem
import com.example.myapplication.data.db.BugEntity
import com.example.myapplication.domain.BugInformation

fun BugItem.toBugInformation(): BugInformation =
    BugInformation(
        bugId,
        bugName,
        appearance,
        color,
        habitat,
        movement,
        surveyResult
    )

fun List<BugItem>.toBugInformation(): List<BugInformation> =
    map { it.toBugInformation() }

fun BugItem.toBugEntity(): BugEntity =
    BugEntity(
        bugId,
        bugName,
        appearance,
        color,
        habitat,
        movement,
        surveyResult
    )

fun List<BugItem>.toBugEntity(): List<BugEntity> =
    map { it.toBugEntity() }
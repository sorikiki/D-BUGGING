package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.BugInformation

@Entity
data class BugEntity(
    @PrimaryKey val bugId: Int? = null,
    val bugName: String? = null,
    val appearance: String? = null,
    val color: String? = null,
    val habitat: String? = null,
    val movement: String? = null,
    val surveyResult: String? = null
)

fun BugEntity.toBugInformation(): BugInformation =
    BugInformation(
        bugId,
        bugName,
        appearance,
        color,
        habitat,
        movement,
        surveyResult
    )

fun List<BugEntity>.toBugInformation(): List<BugInformation> =
    map { it.toBugInformation() }

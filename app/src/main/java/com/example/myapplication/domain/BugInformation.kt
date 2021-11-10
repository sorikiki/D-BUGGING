package com.example.myapplication.domain

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class BugInformation (
    @PrimaryKey
    val bugId: Int? = null,
    val bugName: String? = null,
    val appearance: String? = null,
    val color: String? = null,
    val habitat: String? = null,
    val movement: String? = null,
    val surveyResult: String? = null
): Parcelable
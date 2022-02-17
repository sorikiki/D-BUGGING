package com.example.myapplication.data.repository

import com.example.myapplication.domain.BugInformation
import kotlinx.coroutines.flow.Flow

interface BugRepository {
    val bugs: Flow<List<BugInformation>>

    suspend fun refreshBugs()

    suspend fun addSurveyItem(bugId: Int, userId: String): BugInformation?

    suspend fun getBugItem(bugId: Int): BugInformation?
}
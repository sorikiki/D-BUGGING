package com.example.myapplication.data.repository

import com.example.myapplication.domain.BugInformation
import kotlinx.coroutines.flow.Flow

interface BugRepository {
    suspend fun refreshBugs()

    suspend fun getBugInfo(bugId: Int, userId: String): BugInformation?
}
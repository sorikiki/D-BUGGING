package com.example.myapplication.data.repository

import android.view.WindowId
import com.example.myapplication.data.api.BugApi
import com.example.myapplication.data.api.response.mapper.toBugEntity
import com.example.myapplication.data.api.response.mapper.toBugInformation
import com.example.myapplication.data.db.BugDao
import com.example.myapplication.data.db.toBugInformation
import com.example.myapplication.domain.BugInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BugRepositoryImpl(
    private val bugApi: BugApi,
    private val bugDao: BugDao,
    private val dispatcher: CoroutineDispatcher
) : BugRepository {

    override val bugs: Flow<List<BugInformation>> =
        bugDao.getBugList()
            .distinctUntilChanged()
            .map { it.toBugInformation() }
            .flowOn(dispatcher)

    override suspend fun refreshBugs(): Unit = withContext(dispatcher) {
        bugApi.getBugList()
            .body()
            .also { response ->
                if (response != null) {
                    response.data?.bugList!!.toBugEntity()
                        .let { bugDao.insertBugList(it) }
                }
            }
    }

    override suspend fun doSurvey(bugId: Int, userId: String): BugInformation? {
        return bugApi.addSurveyResult(bugId, userId)
            .body()
            ?.bugItem
            ?.toBugInformation()
    }

    override suspend fun requestSurveyResult(bugInformation: BugInformation): Int? {
        return bugApi.getSurveyResult(bugInformation)
            .body()
            ?.bugInformation
            ?.bugId
    }
}
package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.BugListResponse
import retrofit2.Response
import retrofit2.http.*

interface BugApi {
    // 벌레 목록 요청
    @GET("/bug")
    suspend fun getBugList(): Response<BugListResponse>

    // 설문 결과 추가
    @POST("/bug/{bug_id}/survey")
    suspend fun addSurveyResult(
        @Path("bug_id") bugId: Int,
        @Field("user_id") userId: String
    )
}
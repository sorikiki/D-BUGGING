package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.BugItemResponse
import com.example.myapplication.data.api.response.BugListResponse
import retrofit2.Response
import retrofit2.http.*

interface BugApi {
    // 벌레 목록 요청
    @GET("/bug")
    suspend fun getBugList(): Response<BugListResponse>

    // 벌레 아이템 요청
    @GET("/bug/{bug_id}")
    suspend fun getBugItem(
        @Path("bug_id") bugId: Int
    ): Response<BugItemResponse>

    // 설문 결과 추가
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/bug/survey")
    suspend fun addSurveyResult(
        @Field("bug_id") bugId: Int,
        @Field("user_id") userId: String
    ): Response<BugItemResponse>
}

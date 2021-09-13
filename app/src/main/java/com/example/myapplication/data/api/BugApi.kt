package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.BugItemResponse
import com.example.myapplication.data.api.response.BugListResponse
import com.example.myapplication.data.api.response.ProductItemResponse
import com.example.myapplication.data.api.response.ProductListResponse
import retrofit2.Call
import retrofit2.http.*

interface BugApi {
    // 벌레 목록 요청
    @GET("/bug")
    suspend fun getBugList(): Call<BugListResponse>

    // 벌레 상세 정보 요청
    @GET("/bug/{bug_id}/item")
    suspend fun getBugItem(
        @Path("bug_id") bugId: Int
    ): Call<BugItemResponse>

    // 설문 결과 추가
    @POST("/bug/{bug_id}/survey")
    suspend fun addSurveyResult(
        @Path("bug_id") bugId: Int,
        @Field("user_id") userId: String
    )
}
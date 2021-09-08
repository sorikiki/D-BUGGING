package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.BugItemResponse
import com.example.myapplication.data.api.response.BugListResponse
import com.example.myapplication.data.api.response.ProductItemResponse
import com.example.myapplication.data.api.response.ProductListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BugApi {
    @GET("/bug")
    suspend fun getBugList(): Call<BugListResponse>

    @GET("/bug/{bug_id}/item")
    suspend fun getBugItem(
        @Path("bug_id") bugId: Int
    ): Call<BugItemResponse>

}
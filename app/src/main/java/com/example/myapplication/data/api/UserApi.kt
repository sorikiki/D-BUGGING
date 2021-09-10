package com.example.myapplication.data.api

import com.example.myapplication.data.api.UserApi.Companion.USER_ENDPOINT
import com.example.myapplication.data.api.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface UserApi {
    // 회원가입
    @Headers("accept: application/json", "content-type: application/json")
    @POST(USER_ENDPOINT)
    suspend fun signUpUser(@Body userInfo: UserInfo): Call<UserResponse>

    // 로그인
    @GET(USER_ENDPOINT)
    suspend fun signInUser(
        @Query("id") id: String,
        @Query("password") password: String
    ): Call<UserResponse>

    // 회원탈퇴
    @DELETE(USER_ENDPOINT)
    suspend fun deleteUser(
        @Query("id") id: String,
        @Query("password") password: String
    ): Call<UserResponse>

    companion object {
        const val USER_ENDPOINT = "/users"
    }
}
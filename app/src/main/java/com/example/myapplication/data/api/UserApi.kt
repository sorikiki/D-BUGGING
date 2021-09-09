package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface UserApi {
    // 회원가입
    @Headers("accept: application/json", "content-type: application/json")
    @POST("/user/signup")
    suspend fun signUpUser(@Body userInfo: UserInfo)

    // 로그인
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/user/signin")
    suspend fun signInUser(
        @Field("user_id") userId: String,
        @Field("password") password: String
    ): Call<UserResponse>

    // 회원탈퇴
    @DELETE("/user")
    suspend fun removeUser()
}
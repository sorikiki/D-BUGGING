package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.*
import retrofit2.Call
import retrofit2.Response
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
        @Field("id") id: String,
        @Field("password") password: String
    ): Response<UserResponse>

    // 회원탈퇴
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/user/removal")
    suspend fun removeUser(
        @Field("id") id: String
    )

    // 마이페이지 메인 정보 조회
    @GET("/user/mypage/{user_id}")
    suspend fun getUserInformation(
        @Path("user_id") id: String
    ): Response<UserDetailResponse>

}
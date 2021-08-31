package com.example.myapplication.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface UserApi {
    //todo 회원가입 POST

    //todo 로그인 GET

    //todo 회원탈퇴 POST

    //todo 누적업체이용량+1 POST

    /* Sample
    @GET("api/subway/${BuildConfig.SEOUL_API_ACCESS_KEY}/json/realtimeStationArrival/0/100/{stationName}")
    suspend fun getRealtimeStationArrivals(@Path("stationName") stationName: String): Response<RealtimeStationArrivals>
     */
}
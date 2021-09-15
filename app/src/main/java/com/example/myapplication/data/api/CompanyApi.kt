package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.CompanyItemResponse
import com.example.myapplication.data.api.response.CompanyListResponse
import com.example.myapplication.data.api.response.ReservationCheckResponse
import com.example.myapplication.data.api.response.ReservationResponse
import retrofit2.Call
import retrofit2.http.*

interface CompanyApi {
    // 업체 목록 요청
    @GET("/company")
    suspend fun getCompanyList(): Call<CompanyListResponse>

    // 업체 상세 정보 요청
    @GET("/company/{company_id}/item")
    suspend fun getCompanyItem(
        @Path("company_id") companyId: Int
    ): Call<CompanyItemResponse>

    // 업체 예약
    @Headers("accept: application/json", "content-type: application/json")
    @POST("/company/{company_id}/reservation")
    suspend fun reserveCompany(
        @Path("company_id") companyId: Int,
        @Body reservationInfo: ReservationInfo
    ): Call<ReservationResponse>

    // 예약 정보 요청
    @GET("/company/{company_id}/reservation/{reservation_id}")
    suspend fun checkCompanyReservation(
        @Path("company_id") companyId: Int,
        @Path("reservation_id") reservationId: Int
    ): Call<ReservationCheckResponse>

    // 업체 찜하기 추가
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @POST("/company/interest")
    suspend fun addCompanyItemToWishList(
        @Field("user_id") userId: String,
        @Field("company_id") companyId: Int
    )

    // 업체 찜하기 해제
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @DELETE("/company/interest")
    suspend fun removeCompanyItemFromWishList(
        @Field("user_id") userId: String,
        @Field("company_id") companyId: Int
    )
}
package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.*
import retrofit2.Response
import retrofit2.http.*

interface CompanyApi {
    // 업체 목록 요청
    @GET("/company/{user_id}")
    suspend fun getCompanyList(
        @Path("user_id") userId: String
    ): Response<CompanyListResponse>

    // 업체 예약
    @Headers("accept: application/json", "content-type: application/json")
    @POST("/company/reservation")
    suspend fun reserveCompany(
        @Body reservationInfo: ReservationInfo
    ): Response<ReservationResponse>

    // 예약 정보 요청
    @GET("/company/reservation/{reservation_id}")
    suspend fun checkCompanyReservation(
        @Path("reservation_id") reservationId: Int
    ): Response<ReservationDetailResponse>

    // 업체 찜하기 추가
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/company/interest/insertion")
    suspend fun addCompanyItemToWishList(
        @Field("user_id") userId: String,
        @Field("company_id") companyId: Int
    )

    // 업체 찜하기 해제
    @Headers("accept: application/json", "content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/company/interest/removal")
    suspend fun removeCompanyItemFromWishList(
        @Field("user_id") userId: String,
        @Field("company_id") companyId: Int
    )
}
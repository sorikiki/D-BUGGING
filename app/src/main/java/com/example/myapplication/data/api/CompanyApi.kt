package com.example.myapplication.data.api

import com.example.myapplication.data.api.response.CompanyItemResponse
import com.example.myapplication.data.api.response.CompanyListResponse
import com.example.myapplication.data.api.response.ReservationDetailResponse
import com.example.myapplication.data.api.response.ReservationResponse
import retrofit2.Call
import retrofit2.http.*

interface CompanyApi {
    @GET("/company")
    suspend fun getCompanyList(): Call<CompanyListResponse>

    @GET("/company/{company_id}/item")
    suspend fun getCompanyItem(
        @Path("company_id") companyId: Int
    ): Call<CompanyItemResponse>

    @Headers("accept: application/json", "content-type: application/json")
    @POST("/company/{company_id}/reservation")
    suspend fun reserveCompany(
        @Query("user_id") userId: String,
        @Path("company_id") companyId: Int,
        @Body reservationInfo: ReservationInfo
    ): Call<ReservationResponse>

    @GET("/company/{company_id}/reservation/{reservation_id}")
    suspend fun checkCompanyReservation(
        @Query("company_id") companyId: String,
        @Query("reservation_id") reservationId: String
    ): Call<ReservationDetailResponse>
}
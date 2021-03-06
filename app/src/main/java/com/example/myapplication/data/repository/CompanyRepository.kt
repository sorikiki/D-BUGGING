package com.example.myapplication.data.repository

import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.response.Reservation
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.ReservationInformation
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {
    val companies: Flow<List<CompanyInformation>>

    // 로그인 후 처음 들어올 때 api 요청, 이후에는 Room 에서 가져오기
    suspend fun refreshCompanies()

    // Room 에 변경사항 업데이트
    suspend fun updateCompanyFavorite(companyId: Int)

    // MySQL 에도 변경사항 업데이트
    suspend fun updateRemoteCompanyList()

    suspend fun makeReservation(reservationInfo: ReservationInfo): Int?

    suspend fun requestInformation(reservationId: Int): ReservationInformation?

    suspend fun requestNumberOfReservations(userId: String): Int?
}
package com.example.myapplication.data.repository

import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.domain.CompanyInformation

interface CompanyRepository {
    suspend fun getCompanyList(): List<CompanyInformation>?

    suspend fun updateCompanyFavorite(companyId: Int, isFavorite: Boolean)

    suspend fun makeReservation(companyId: Int, reservationInfo: ReservationInfo)

    suspend fun requestInformation(companyId: Int, reservationId: Int)
}
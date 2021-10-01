package com.example.myapplication.data.repository

import com.example.myapplication.domain.CompanyInformation

interface CompanyRepository {
    suspend fun getCompanyList(): List<CompanyInformation>?

    suspend fun updateCompanyFavorite()
}
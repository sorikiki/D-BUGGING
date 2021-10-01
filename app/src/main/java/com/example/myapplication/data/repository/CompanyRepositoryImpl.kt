package com.example.myapplication.data.repository

import com.example.myapplication.data.api.CompanyApi

class CompanyRepositoryImpl(
    private val companyApi: CompanyApi
): CompanyRepository {
    override suspend fun getCompanyList() {
        TODO("Not yet implemented")
    }
}
package com.example.myapplication.data.repository

interface CompanyRepository {
    suspend fun getCompanyList()
}
package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.response.mapper.toCompanyInformation
import com.example.myapplication.domain.CompanyInformation

class CompanyRepositoryImpl(
    private val companyApi: CompanyApi
): CompanyRepository {
    override suspend fun getCompanyList(): List<CompanyInformation>? {
        companyApi.getCompanyList()
            .body()
            .also { response ->
                Log.d("response", response.toString())
                if (response != null) {
                    return response.data?.companyList?.toCompanyInformation()
                }
            }
        return null
    }

    override suspend fun updateCompanyFavorite() {
        TODO("Not yet implemented")
    }
}
package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.response.mapper.toCompanyInformation
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.domain.CompanyInformation

class CompanyRepositoryImpl(
    private val companyApi: CompanyApi,
    private val preferenceManager: PreferenceManager
): CompanyRepository {
    override suspend fun getCompanyList(): List<CompanyInformation>? {
        val userId = preferenceManager.getUserId()

        companyApi.getCompanyList(userId!!)
            .body()
            .also { response ->
                Log.d("response", response.toString())
                if (response != null) {
                    return response.data?.companyList?.toCompanyInformation()
                }
            }
        return null
    }

    override suspend fun updateCompanyFavorite(companyId: Int, isFavorite: Boolean) {
        val userId = preferenceManager.getUserId()

        if (!isFavorite) {
            companyApi.addCompanyItemToWishList(userId!!, companyId)
        } else {
            companyApi.removeCompanyItemFromWishList(userId!!, companyId)
        }
    }
}
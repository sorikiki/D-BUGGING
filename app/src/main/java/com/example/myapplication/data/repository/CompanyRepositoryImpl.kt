package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.response.CompanyItem
import com.example.myapplication.data.api.response.mapper.toCompanyEntity
import com.example.myapplication.data.api.response.mapper.toCompanyInformation
import com.example.myapplication.data.db.CompanyDao
import com.example.myapplication.data.db.toCompanyInformation
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.domain.CompanyInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class CompanyRepositoryImpl(
    private val companyApi: CompanyApi,
    private val preferenceManager: PreferenceManager,
    private val companyDao: CompanyDao,
    private val dispatcher: CoroutineDispatcher
) : CompanyRepository {

    override val companies: Flow<List<CompanyInformation>> =
        companyDao.getCompanyList()
            .distinctUntilChanged()
            .map { it.toCompanyInformation().sortedByDescending { it.isCompanyInterested } }
            .flowOn(dispatcher)

    override suspend fun refreshCompanies(): Unit = withContext(dispatcher) {
        companyApi.getCompanyList(getCurrentUser()!!)
            .body()
            .also { response ->
                if (response != null) {
                    response.data?.companyList!!.toCompanyEntity()
                        .let { companyDao.insertCompanyList(it) }
                }
            }
    }

    override suspend fun updateCompanyFavorite(companyId: Int) {
        val companyItem = companyDao.getCompanyItem(companyId)
        companyDao.updateCompany(companyItem.copy(isCompanyInterested = !companyItem.isCompanyInterested!!))
    }

    override suspend fun updateRemoteCompanyList() {
        companyApi.getCompanyList(getCurrentUser()!!)
            .body()
            ?.data
            ?.companyList
            ?.map { companyItem ->
                val companyId = companyItem.companyId!!
                val companyEntity = companyDao.getCompanyItem(companyId)
                val userId = getCurrentUser()!!
                if (companyEntity.isCompanyInterested != companyItem.isCompanyInterested) {
                    if (companyEntity.isCompanyInterested == true) {
                        companyApi.addCompanyItemToWishList(userId, companyId)
                    } else {
                        companyApi.removeCompanyItemFromWishList(userId, companyId)
                    }
                }
            }
    }

    private fun getCurrentUser(): String? {
        return preferenceManager.getUserId()
    }


}
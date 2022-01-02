package com.example.myapplication.data.repository

import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.response.mapper.toCompanyEntity
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.UserApi
import com.example.myapplication.data.api.response.mapper.toReservationInformation
import com.example.myapplication.data.db.CompanyDao
import com.example.myapplication.data.db.toCompanyInformation
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.ReservationInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class CompanyRepositoryImpl(
    private val userApi: UserApi,
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
        companyApi.getCompanyList(getCurrentUserId()!!)
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
        companyApi.getCompanyList(getCurrentUserId()!!)
            .body()
            ?.data
            ?.companyList
            ?.map { companyItem ->
                val companyId = companyItem.companyId!!
                val companyEntity = companyDao.getCompanyItem(companyId)
                val userId = getCurrentUserId()!!
                if (companyEntity.isCompanyInterested != companyItem.isCompanyInterested) {
                    if (companyEntity.isCompanyInterested == true) {
                        companyApi.addCompanyItemToWishList(userId, companyId)
                    } else {
                        companyApi.removeCompanyItemFromWishList(userId, companyId)
                    }
                }
            }
    }

    override suspend fun makeReservation(reservationInfo: ReservationInfo): Int? {
        return companyApi.reserveCompany(reservationInfo)
            .body()
            ?.reservation
            ?.reservationId
    }

    override suspend fun requestInformation(reservationId: Int): ReservationInformation? {

        return companyApi.checkCompanyReservation(reservationId)
            .body()
            ?.reservationCheck
            ?.toReservationInformation()
    }

    private fun getCurrentUserId(): String? {
        return preferenceManager.getUserId()
    }
}
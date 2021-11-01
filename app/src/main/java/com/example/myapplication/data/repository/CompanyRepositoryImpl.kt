package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.response.ReservationCheckResponse
import com.example.myapplication.data.api.response.ReservationResponse
import com.example.myapplication.data.api.response.mapper.toCompanyInformation
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.domain.CompanyInformation
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

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

    override suspend fun makeReservation(companyId: Int, reservationInfo: ReservationInfo) {
        val call = companyApi.reserveCompany(companyId, reservationInfo)

        call.enqueue(object : retrofit2.Callback<ReservationResponse> {
            override fun onFailure(call: Call<ReservationResponse>, t: Throwable) {
                Log.d("response", t.toString())
            }

            override fun onResponse(
                call: Call<ReservationResponse>,
                response: Response<ReservationResponse>
            ) {
                if(response.isSuccessful) {
                    Log.d("response", response?.body().toString())
                    //response.body()?.reservation?.reservationId
                }
            }
        })
    }

    override suspend fun requestInformation(companyId: Int, reservationId : Int) {
        val callReserveCompany = companyApi.checkCompanyReservation(companyId, reservationId)

        callReserveCompany.enqueue(object : retrofit2.Callback<ReservationCheckResponse> {
            override fun onResponse(
                call: Call<ReservationCheckResponse>,
                response: Response<ReservationCheckResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("response", response?.body().toString())
                }
            }

            override fun onFailure(call: Call<ReservationCheckResponse>, t: Throwable) {
                Log.d("response", t.toString())
            }
        })
    }
}
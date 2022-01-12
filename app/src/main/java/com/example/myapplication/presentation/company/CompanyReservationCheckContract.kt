package com.example.myapplication.presentation.company

import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.response.CurrentUser
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.ReservationInformation
import com.example.myapplication.domain.UserInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView
import kotlinx.coroutines.flow.Flow

interface CompanyReservationCheckContract {
    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun processReservationSuccess()
    }

    interface Presenter : BasePresenter {
        fun makeReservation(reservationInfo: ReservationInfo, companyInformation: CompanyInformation, currentUser: UserInformation)
    }
}
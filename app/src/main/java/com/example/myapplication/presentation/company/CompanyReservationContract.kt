package com.example.myapplication.presentation.company

import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface CompanyReservationContract {
    interface View : BaseView<Presenter> {
        fun processReservationSuccess()

        fun showLoadingIndicator()

        fun hideLoadingIndicator()
    }

    interface Presenter : BasePresenter{
        fun makeReservation(companyId: ReservationInfo, reservationInfo: ReservationInfo)
    }
}
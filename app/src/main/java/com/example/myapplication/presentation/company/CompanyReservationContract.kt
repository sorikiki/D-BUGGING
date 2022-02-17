package com.example.myapplication.presentation.company

import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.UserInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface CompanyReservationContract {
    interface View : BaseView<Presenter> {
        fun submitReservationInfo(userInfo: UserInformation?, reservationInfo: ReservationInfo?)

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun setUserInfoVisible(userInfo: UserInformation?)
    }

    interface Presenter : BasePresenter {
        val currentUser: UserInformation?

        fun getUserInfo(reservationInfo: ReservationInfo)
    }
}
package com.example.myapplication.presentation.register

import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface RegisterContract {
    interface View : BaseView<Presenter> {
        fun processSignUpSuccess()

        fun showLoadingIndicator()

        fun hideLoadingIndicator()
    }

    interface Presenter : BasePresenter {
        fun signUpUser(userInfo: UserInfo)
    }
}
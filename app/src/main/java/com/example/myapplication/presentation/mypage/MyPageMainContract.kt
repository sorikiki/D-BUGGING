package com.example.myapplication.presentation.mypage

import com.example.myapplication.domain.UserInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

class MyPageMainContract {

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showUserInformation(userInfo: UserInformation?)
    }

    interface Presenter : BasePresenter {
        fun getUserInformation()
    }
}
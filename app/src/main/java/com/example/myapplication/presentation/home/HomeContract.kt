package com.example.myapplication.presentation.home

import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface HomeContract {
    interface View : BaseView<Presenter> {
        fun moveToLoginScreen()

        fun showLoadingIndicator()

        fun hideLoadingIndicator()
    }

    interface Presenter : BasePresenter {
        fun setCurrentUserId(): String

        fun setCurrentUserName(): String

        fun logOutUser()

        fun clearUser(userId: String)
    }
}
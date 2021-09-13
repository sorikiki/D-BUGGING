package com.example.myapplication.presentation.login

import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface LoginContract {
    interface View : BaseView<Presenter> {
        fun getUserInput()

        fun processLoginSuccess()

        fun processLoginFail()

        fun showLoadingIndicator()

        fun hideLoadingIndicator()
    }

    interface Presenter : BasePresenter {
        fun signInUser(id: String, password: String)
    }
}
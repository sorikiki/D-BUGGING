package com.example.myapplication.presentation.survey

import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface SurveyResultContract {
    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()
    }

    interface Presenter : BasePresenter {
        fun setCurrentUserName(): String
    }
}
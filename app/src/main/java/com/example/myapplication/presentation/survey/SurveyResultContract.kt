package com.example.myapplication.presentation.survey

import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface SurveyResultContract {
    interface View : BaseView<Presenter> {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showBugResult(bugInformation: BugInformation)
    }

    interface Presenter : BasePresenter {
        fun setCurrentUserName(): String

        fun getBugInformation(bugId: Int, isAddingSurveyItem: Boolean)
    }
}
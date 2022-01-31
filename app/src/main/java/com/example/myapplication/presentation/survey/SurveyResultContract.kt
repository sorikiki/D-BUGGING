package com.example.myapplication.presentation.survey

import com.example.myapplication.domain.BugInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface SurveyResultContract {
    interface View : BaseView<Presenter> {
        fun showBugInfo(bugInformation: BugInformation?)

        fun showLoadingIndicator()

        fun hideLoadingIndicator()
    }

    interface Presenter : BasePresenter {
        val currentBug: BugInformation?

        fun setCurrentUserName(): String

        fun getBugInfo(bugId: Int, userId: String)
    }
}
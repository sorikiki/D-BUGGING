package com.example.myapplication.presentation.bug

import com.example.myapplication.domain.BugInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface BugListContract {
    interface View : BaseView<Presenter> {
        fun showBugItems(items: List<BugInformation>)

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorMessage()
    }

    interface Presenter : BasePresenter {

    }
}
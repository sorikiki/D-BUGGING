package com.example.myapplication.presentation.bug

import com.example.myapplication.domain.BugInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface BugSearchContract {
    interface View : BaseView<Presenter> {

        fun showBugItems(items: List<BugInformation>)
    }

    interface Presenter : BasePresenter {

        fun filterBugs(query: String)
    }
}
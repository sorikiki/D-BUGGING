package com.example.myapplication.presentation.company

import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface CompanyListContract {
    interface View : BaseView<Presenter> {
        fun showCompanyItems(items: List<CompanyInformation>)

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorMessage()
    }

    interface Presenter : BasePresenter {
        fun getCompanyList()
    }
}
package com.example.myapplication.presentation.company

import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface CompanySearchContract {
    interface View : BaseView<Presenter> {

        fun showCompanyItems(items: List<CompanyInformation>)
    }

    interface Presenter : BasePresenter {

        fun updateCompanyFavorite(companyId: Int)

        fun filterStations(query: String)
    }

}
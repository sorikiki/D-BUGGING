package com.example.myapplication.presentation.product

import com.example.myapplication.domain.ProductInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface ProductListContract {
    interface View : BaseView<Presenter> {
        fun showProductItems(items: List<ProductInformation>)

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorMessage()
    }

    interface Presenter : BasePresenter {

        fun updateProductFavorite(companyId: Int)
    }
}
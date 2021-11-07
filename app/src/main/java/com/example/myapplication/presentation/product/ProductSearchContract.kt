package com.example.myapplication.presentation.product

import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.ProductInformation
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface ProductSearchContract {
    interface View : BaseView<Presenter> {

        fun showProductItems(items: List<ProductInformation>)
    }

    interface Presenter : BasePresenter {

        fun updateProductFavorite(productId: Int)

        fun filterProducts(query: String)
    }
}
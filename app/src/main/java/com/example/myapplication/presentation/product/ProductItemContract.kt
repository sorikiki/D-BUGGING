package com.example.myapplication.presentation.product

import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface ProductItemContract {
    interface View : BaseView<Presenter> {
        fun toggleFavoriteView()
    }

    interface Presenter : BasePresenter {
        fun updateProductFavorite(productId: Int)
    }

}
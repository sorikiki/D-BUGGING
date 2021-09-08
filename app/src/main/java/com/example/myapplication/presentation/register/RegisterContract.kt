package com.example.myapplication.presentation.register

import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface RegisterContract {
    interface View : BaseView<Presenter> {


    }

    interface Presenter : BasePresenter {

    }
}
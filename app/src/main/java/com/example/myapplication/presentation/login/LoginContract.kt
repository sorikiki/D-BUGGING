package com.example.myapplication.presentation.login

import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface LoginContract {
    interface View : BaseView<Presenter> {


    }

    interface Presenter : BasePresenter {

    }
}
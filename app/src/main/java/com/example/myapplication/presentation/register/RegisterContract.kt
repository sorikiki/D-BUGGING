package com.example.myapplication.presentation.register

import android.widget.EditText
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.presentation.BasePresenter
import com.example.myapplication.presentation.BaseView

interface RegisterContract {
    interface View : BaseView<Presenter> {
        fun putUserInput()

        fun processRegisterSuccess()

        fun processRegisterFail()

    }

    interface Presenter : BasePresenter {
        fun signUpUser(UserInfo: UserInfo);
    }
}
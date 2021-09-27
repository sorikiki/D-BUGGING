package com.example.myapplication.presentation.register

import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class RegisterPresenter(
    private val userRepository: UserRepository,
    private val view:RegisterContract.View
):RegisterContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {}

    override fun onDestroyView() {}

    override fun signUpUser(UserInfo: UserInfo) {
        scope.launch {
            val isSucceed = userRepository.registerUser(UserInfo)

            if (isSucceed) {
                view.processRegisterSuccess()
            } else {
                view.processRegisterFail()
            }
        }
    }

}
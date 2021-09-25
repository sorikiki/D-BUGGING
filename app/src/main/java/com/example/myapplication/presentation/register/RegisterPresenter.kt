package com.example.myapplication.presentation.register

import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.presentation.login.LoginContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class RegisterPresenter(
    private val userRepository: UserRepository,
    private val view:RegisterFragment
    ): RegisterContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun signUpUser(userInfo: UserInfo) {
        scope.launch {
            userRepository.registerUser(userInfo)
            view.processSignUpSuccess()
        }
    }

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }


}
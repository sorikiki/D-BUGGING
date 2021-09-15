package com.example.myapplication.presentation.login

import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LoginPresenter(
    private val userRepository: UserRepository,
    private val view: LoginContract.View
): LoginContract.Presenter   {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {}

    override fun onDestroyView() {}

    override fun signInUser(id: String, password: String) {

        scope.launch {
            view.hideLoadingIndicator()
            val isSucceed = userRepository.processLogIn(id, password)

            if (isSucceed) {
                view.showLoadingIndicator()
                view.processLoginSuccess()
            } else {
                view.hideLoadingIndicator()
                view.processLoginFail()
            }
        }
    }
}
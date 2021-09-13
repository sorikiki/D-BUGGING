package com.example.myapplication.presentation.login

import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class LoginPresenter(
    private val userRepository: UserRepository,
    private val view: LoginActivity
): LoginContract.Presenter   {
    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }

    override suspend fun signInUser() {
        val id = view.getUserInput().first
        val password = view.getUserInput().second
        userRepository.processLogIn(id, password)
    }
}
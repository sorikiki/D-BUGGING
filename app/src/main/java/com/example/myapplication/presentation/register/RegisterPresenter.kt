package com.example.myapplication.presentation.register

import com.example.myapplication.data.repository.UserRepository

class RegisterPresenter(
    private val userRepository: UserRepository,
    private val view:RegisterFragment
    ) {

}
package com.example.myapplication.presentation.home

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.data.preference.SharedPreferenceManager
import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.*

class HomePresenter(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val view: HomeActivity
) : HomeContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        setCurrentUserId()
        setCurrentUserName()
    }

    override fun setCurrentUserId(): String {
        return userRepository.getCurrentUserId() ?: NO_USER
    }

    override fun setCurrentUserName(): String {
        return userRepository.getCurrentUserName() ?: NO_USER
    }

    override fun logOutUser() {
        scope.launch {
            withContext(Dispatchers.IO) {
                userRepository.logOutUser()
            }
            view.moveToLoginScreen()
        }
    }

    override fun clearUser(id: String) {
        scope.launch {
            userRepository.clearUser(id)
        }
    }

    override fun onDestroyView() {}

    companion object {
        const val NO_USER = "NO_USER"
    }

}
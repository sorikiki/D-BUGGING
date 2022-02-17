package com.example.myapplication.presentation.home

import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.*

class HomePresenter(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val view: HomeActivity
) : HomeContract.Presenter {

    lateinit var userId: String

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        setAccumulatedNumberOfReservations()
    }

    override fun setCurrentUserId(): String {
        userId = userRepository.getPreferenceUserId() ?: NO_USER
        return userId
    }

    override fun setCurrentUserName(): String {
        return userRepository.getPreferenceUserName() ?: NO_USER
    }

    override fun setAccumulatedNumberOfReservations() {
        scope.launch {
            val number = companyRepository.requestNumberOfReservations(userId)
            view.showAccumulatedNumberOfReservations(number)
        }
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
package com.example.myapplication.presentation.company

import android.util.Log
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.domain.UserInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CompanyReservationPresenter (
    private val userRepository: UserRepository,
    val view: CompanyReservationFragment
) : CompanyReservationContract.Presenter {

    override var currentUser: UserInformation? = null

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        getCurrentUserInfo()
    }

    override fun getUserInfo(reservationInfo: ReservationInfo) {
        scope.launch {
            view.submitReservationInfo(currentUser, reservationInfo)
        }
    }

    private fun getCurrentUserInfo() {
        scope.launch {
            currentUser = userRepository.getCurrentUserInfo()
            view.setUserInfoVisible(currentUser)
        }

    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }

}
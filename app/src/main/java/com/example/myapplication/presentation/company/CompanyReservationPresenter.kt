package com.example.myapplication.presentation.company

import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.domain.UserInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CompanyReservationPresenter (
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    val view: CompanyReservationFragment
) : CompanyReservationContract.Presenter {

    lateinit var userInformation: UserInformation

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun makeReservation(reservationInfo: ReservationInfo) {
        scope.launch {
            val reservationId = companyRepository.makeReservation(reservationInfo)
            if (reservationId != null) {
                view.processReservationSuccess(reservationId)
            }
        }
    }

    override fun getCurrentUserInfo(): UserInformation {
        scope.launch {
            userInformation = userRepository.getCurrentUserInfo()!!
        }
        return userInformation
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }

}
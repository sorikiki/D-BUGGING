package com.example.myapplication.presentation.company

import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.UserInformation
import kotlinx.coroutines.*

class CompanyReservationCheckPresenter(
    private val companyRepository: CompanyRepository,
    val view: CompanyReservationCheckFragment
) : CompanyReservationCheckContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun makeReservation(
        reservationInfo: ReservationInfo,
        companyInfo: CompanyInformation,
        currentUser: UserInformation
    ) {
        scope.launch {
            view.showLoadingIndicator()
            withContext(Dispatchers.IO) {
                companyRepository.makeReservation(
                    ReservationInfo(
                        companyId = companyInfo.companyId!!,
                        userId = currentUser.userId!!,
                        bugName = reservationInfo.bugName,
                        firstFoundDate = reservationInfo.firstFoundDate,
                        firstFoundPlace = reservationInfo.firstFoundPlace,
                        hasBugBeenShown = reservationInfo.hasBugBeenShown,
                        wantedDate = reservationInfo.wantedDate,
                        reserveDateTime = reservationInfo.reserveDateTime,
                        availableVisitTime = reservationInfo.availableVisitTime,
                        extraMessage = reservationInfo.extraMessage
                    )
                )
            }
            view.hideLoadingIndicator()
            view.processReservationSuccess()
        }


    }

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }


//    override fun getReservationInfo(
//        currentUser: UserInformation?,
//        reservationInfo: ReservationInfo?,
//        companyName: String?
//    ) {
//        scope.launch {
//            view.showLoadingIndicator()
//            val reservationItem = withContext(Dispatchers.IO) {
//                companyRepository.requestInformation(reservationId!!)
//                    ?.copy(
//                        companyName = companyName,
//                        userName = currentUser?.userName,
//                        userContactNumber = currentUser?.contactNumber,
//                        userAddress = "${currentUser?.roadAddress} ${currentUser?.detailAddress}",
//                        numOfRooms = currentUser?.numOfRooms
//                    )
//            }
//            view.showReservationInfo(reservationItem)
//            view.hideLoadingIndicator()
//        }
//    }

}
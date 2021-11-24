package com.example.myapplication.presentation.company

import androidx.leanback.widget.Presenter
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.domain.CompanyInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CompanyReservationPresenter (
    private val companyRepository: CompanyRepository,
    val view: CompanyReservationFragment
) : CompanyReservationContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun makeReservation(companyId: Int, reservationInfo: ReservationInfo) {
        scope.launch {
            companyRepository.makeReservation(companyId, reservationInfo)
            view.processReservationSuccess()
        }
    }

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }

}
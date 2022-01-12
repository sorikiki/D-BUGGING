package com.example.myapplication.presentation.company

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.response.CompanyItem
import com.example.myapplication.data.api.response.CurrentUser
import com.example.myapplication.databinding.FragmentCompanyReservationCheckBinding
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.ReservationInformation
import com.example.myapplication.domain.UserInformation
import org.koin.android.scope.ScopeFragment

class CompanyReservationCheckFragment : ScopeFragment(), CompanyReservationCheckContract.View {
    private var binding: FragmentCompanyReservationCheckBinding? = null

    override val presenter: CompanyReservationCheckContract.Presenter by inject()

    lateinit var reservationInfo: ReservationInfo
    lateinit var companyInfo: CompanyInformation
    lateinit var currentUser: UserInformation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCompanyReservationCheckBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        reservationInfo = arguments?.getParcelable("reservation")!!
        companyInfo = arguments?.getParcelable("company")!!
        currentUser = arguments?.getParcelable("user")!!
        showReservationInfo(currentUser, reservationInfo, companyInfo.companyName)
    }

    private fun bindViews() {
        binding?.btnSubmit?.setOnClickListener {
            presenter.makeReservation(reservationInfo, companyInfo, currentUser)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showReservationInfo(
        currentUser: UserInformation?,
        reservationInfo: ReservationInfo?,
        companyName: String?
    ) {
        binding?.apply {
            tvCompanyName.text = companyName
            val visitDate = reservationInfo?.wantedDate?.split("-")
            tvVisitDateStart.text = visitDate?.get(0).plus("-")
            tvVisitDateEnd.text = visitDate?.get(1)
            tvVisitTime.text = reservationInfo?.availableVisitTime.toString() //
            tvApplicant.text = currentUser?.userName
            tvContact.text = currentUser?.contactNumber
            tvVisitAddress.text = "${currentUser?.roadAddress} ${currentUser?.detailAddress}"
            etBug.text = reservationInfo?.bugName
            etFindDate.text = reservationInfo?.firstFoundDate
            etFindSpot.text = reservationInfo?.firstFoundPlace
            etRoomCount.text = currentUser?.numOfRooms.toString()
            bugExperience.text = if (reservationInfo?.hasBugBeenShown == 1) "있음" else "없음"
            additionalContent.text = reservationInfo?.extraMessage
        }
    }

    override fun processReservationSuccess() {
        findNavController().navigate(R.id.action_companyReservationCheckFragment_to_companyReservationCompletedFragment)
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.contentContainer?.visibility = View.GONE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.visibility = View.GONE
        binding?.contentContainer?.visibility = View.VISIBLE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
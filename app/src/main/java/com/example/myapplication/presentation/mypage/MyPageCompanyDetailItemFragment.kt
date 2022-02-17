package com.example.myapplication.presentation.mypage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.databinding.FragmentMypageCompanyDetailItemBinding
import com.example.myapplication.domain.ReservationInformation
import com.example.myapplication.domain.UserInformation
import com.example.myapplication.ext.dateTimeFormatter
import com.example.myapplication.ext.newDateFormatter
import com.example.myapplication.ext.newDateTimeFormatter

class MyPageCompanyDetailItemFragment : Fragment() {
    private var binding: FragmentMypageCompanyDetailItemBinding? = null

    lateinit var userInfo: UserInformation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMypageCompanyDetailItemBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        val reservationInfo = arguments?.getParcelable<ReservationInformation>("reservationInfo")!!
        userInfo = arguments?.getParcelable("userInfo")!!

        binding?.tvName?.text = "현재 ${userInfo.userName} 님은"

        when (reservationInfo.processState) {
            0 -> showFirstStateInfo(reservationInfo)
            1 -> showSecondStateInfo(reservationInfo)
            2 -> showThirdStateInfo(reservationInfo)
            else -> showFourthStateInfo(reservationInfo)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showFirstStateInfo(reservationInfo: ReservationInformation) {
        binding?.apply {
            process0Container.visibility = View.VISIBLE
            process1Container.visibility = View.GONE
            process2Container.visibility = View.GONE

            ivState0.setImageResource(R.drawable.bg_green_circle)
            ivState1.setImageResource(R.drawable.bg_grey_circle)
            ivState2.setImageResource(R.drawable.bg_grey_circle)
            ivState3.setImageResource(R.drawable.bg_grey_circle)

            tvState.text = "예약 접수 상태입니다"
            tvState0ReserveDateTime.text =
                newDateTimeFormatter.format(dateTimeFormatter.parse(reservationInfo.reserveDateTime!!)!!)
            tvState0CompanyName.text = reservationInfo.companyName
            tvState0ReserveDate.text =
                newDateFormatter.format(dateTimeFormatter.parse(reservationInfo.reserveDateTime!!)!!)
            tvState0VisitDate.text = reservationInfo.wantedDate
            tvState0VisitTime.text = reservationInfo.wantedTime
            tvState0Applicant.text = userInfo.userName
            tvState0ContactNumbers.text = userInfo.contactNumber
            tvState0Address.text =
                "${userInfo.roadAddress} ${userInfo.detailAddress} ${userInfo.zipCode}"
            tvState0BugName.text = reservationInfo.bugType
            tvState0FoundDate.text =
                newDateFormatter.format(dateTimeFormatter.parse(reservationInfo.firstFoundDate)!!)
            tvState0FoundPlace.text = reservationInfo.firstFoundPlace
            tvState0NumOfRooms.text = userInfo.numOfRooms.toString()
            tvState0HasBugBeenShown.text = if (reservationInfo.hasBugBeenShown == 1) "있음" else "없음"
            tvState0ExtraMessage.text = reservationInfo.extraMessage
        }
    }

    private fun showSecondStateInfo(reservationInfo: ReservationInformation) {
        showFirstStateInfo(reservationInfo)

        binding?.apply {
            process0Container.visibility = View.VISIBLE
            process1Container.visibility = View.VISIBLE
            process2Container.visibility = View.GONE

            ivState0.setImageResource(R.drawable.bg_grey_circle)
            ivState1.setImageResource(R.drawable.bg_green_circle)
            ivState2.setImageResource(R.drawable.bg_grey_circle)
            ivState3.setImageResource(R.drawable.bg_grey_circle)

            tvState.text = "업체 확인중 상태입니다"
            tvState1ConfirmDateTime.text =
                newDateTimeFormatter.format(dateTimeFormatter.parse(reservationInfo.confirmDateTime!!)!!)
            tvState1EngineerName.text = reservationInfo.engineerName
            tvState1EngineerContactNumbers.text = reservationInfo.engineerContactNumber
            tvState1ExpectedEstimate.text = reservationInfo.expectedEstimate.toString()
            tvState1VisitDateTime.text =
                newDateTimeFormatter.format(dateTimeFormatter.parse(reservationInfo.visitDateTime!!)!!)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showThirdStateInfo(reservationInfo: ReservationInformation) {
        showFirstStateInfo(reservationInfo)
        showSecondStateInfo(reservationInfo)

        binding?.apply {
            process0Container.visibility = View.VISIBLE
            process1Container.visibility = View.VISIBLE
            process2Container.visibility = View.VISIBLE

            ivState0.setImageResource(R.drawable.bg_grey_circle)
            ivState1.setImageResource(R.drawable.bg_grey_circle)
            ivState2.setImageResource(R.drawable.bg_green_circle)
            ivState3.setImageResource(R.drawable.bg_grey_circle)

            tvState.text = "방문예약 접수완료 상태입니다"
            tvState2ReserveCompletedDateTime.text =
                newDateTimeFormatter.format(dateTimeFormatter.parse(reservationInfo.reserveCompletedDateTime!!)!!)
            tvState2CompanyName.text = reservationInfo.companyName
            tvState2BugType.text = reservationInfo.bugType
            tvState2VisitDateTime.text =
                newDateTimeFormatter.format(dateTimeFormatter.parse(reservationInfo.visitDateTime!!)!!)
            tvState2Address.text =
                "${userInfo.roadAddress} ${userInfo.detailAddress} ${userInfo.zipCode}"
            tvState2EngineerName.text = reservationInfo.engineerName
            tvState2ContactNumbers.text = reservationInfo.engineerContactNumber
            tvState2ExpectedEstimate.text = reservationInfo.expectedEstimate.toString()
        }
    }

    private fun showFourthStateInfo(reservationInfo: ReservationInformation) {
        showFirstStateInfo(reservationInfo)
        showSecondStateInfo(reservationInfo)
        showThirdStateInfo(reservationInfo)

        binding?.apply {

            process0Container.visibility = View.VISIBLE
            process1Container.visibility = View.VISIBLE
            process2Container.visibility = View.VISIBLE

            ivState0.setImageResource(R.drawable.bg_grey_circle)
            ivState1.setImageResource(R.drawable.bg_grey_circle)
            ivState2.setImageResource(R.drawable.bg_grey_circle)
            ivState3.setImageResource(R.drawable.bg_green_circle)

            tvState.text = "케어 종료 상태입니다"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
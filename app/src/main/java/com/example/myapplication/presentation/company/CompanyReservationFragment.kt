package com.example.myapplication.presentation.company

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.databinding.FragmentCompanyReservationBinding
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.UserInformation
import org.koin.android.scope.ScopeFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CompanyReservationFragment : ScopeFragment(), CompanyReservationContract.View {
    private var binding: FragmentCompanyReservationBinding? = null

    private lateinit var companyInfo: CompanyInformation

    override val presenter: CompanyReservationContract.Presenter by inject()

    private lateinit var bugName: String
    private lateinit var firstFoundDate: String
    private lateinit var firstFoundPlace: String
    private lateinit var wantedStartDate: String
    private lateinit var wantedEndDate: String
    private lateinit var reserveDateTime: String
    private lateinit var extraMessage: String
    private var hasBugBeenShown = 0
    private var availableVisitTime: String = "방문시간 미입력"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCompanyReservationBinding.inflate(inflater, container, false)
        .also {
            binding = it
        }
        .root

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()

        presenter.onViewCreated()
    }

    private fun initViews() {
        companyInfo = arguments?.getParcelable("company")!!
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindViews() {
        binding?.apply {
            hasBugBeenShownContainer.setOnCheckedChangeListener { group: RadioGroup?, checkedId: Int ->
                when (group?.id) {
                    R.id.has_bug_been_shown_container -> {
                        when (checkedId) {
                            R.id.rb_yes -> hasBugBeenShown = 1
                            R.id.rb_no -> hasBugBeenShown = 0
                        }
                    }
                }
            }

            wantedTimeContainer.setOnCheckedChangeListener { group: RadioGroup, checkedId: Int ->
                when (group.id) {
                    R.id.wanted_time_container ->
                        when (checkedId) {
                            R.id.rb_anytime -> availableVisitTime =
                                binding?.rbAnytime?.text.toString()
                            R.id.rb_9_to_12 -> availableVisitTime =
                                binding?.rb9To12?.text.toString()
                            R.id.rb_13_to_16 -> availableVisitTime =
                                binding?.rb13To16?.text.toString()
                            R.id.rb_16_to_19 -> availableVisitTime =
                                binding?.rb16To19?.text.toString()
                            R.id.rb_19_to_22 -> availableVisitTime =
                                binding?.rb19To22?.text.toString()
                        }
                }
            }

            btnSubmit.setOnClickListener {
                bugName = etBug.text.toString()
                firstFoundDate = etFindDate.text.toString()
                firstFoundPlace = etFindSpot.text.toString()
                wantedStartDate =
                    tvWantedStartYear.text.toString() + "년 " + tvWantedStartMonth.text.toString() + "월 " + tvWantedStartDay.text.toString() + "일"
                wantedEndDate =
                    tvWantedEndYear.text.toString() + "년 " + tvWantedEndMonth.text.toString() + "월 " + tvWantedEndDay.text.toString() + "일"
                val date = Date(System.currentTimeMillis())
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("ko", "KR"))
                reserveDateTime = dateFormat.format(date)
                extraMessage = etExtraMessage.text.toString()

                if (bugName.isBlank() || firstFoundDate.isBlank() || firstFoundPlace.isBlank()) {
                    Toast.makeText(context, "필수 입력 내용을 모두 작성해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    succeedReservation(
                        bugName,
                        firstFoundDate,
                        firstFoundPlace,
                        wantedStartDate,
                        wantedEndDate,
                        reserveDateTime,
                        availableVisitTime,
                        extraMessage
                    )
                }
            }
        }
    }


    private fun succeedReservation(
        bugName: String,
        firstFoundDate: String,
        firstFoundPlace: String,
        wantedStartDate: String,
        wantedEndDate: String,
        reserveDateTime: String,
        wantedTime: String,
        extraMessage: String? = null
    ) {
        presenter.getUserInfo(
            ReservationInfo(
                userId = presenter.currentUser?.userId!!,
                companyId = companyInfo.companyId!!,
                bugName = bugName,
                firstFoundDate = firstFoundDate,
                firstFoundPlace = firstFoundPlace,
                hasBugBeenShown = hasBugBeenShown,
                wantedDate = "$wantedStartDate - $wantedEndDate",
                reserveDateTime = reserveDateTime,
                availableVisitTime = wantedTime,
                extraMessage = extraMessage
            )
        )
    }

    override fun submitReservationInfo(userInfo: UserInformation?, reservationInfo: ReservationInfo?) {
        val bundle = bundleOf(
            "reservation" to reservationInfo,
            "company" to companyInfo,
            "user" to userInfo
        );
        view?.findNavController()?.navigate(
            R.id.action_companyReservationFragment_to_companyReservationCheckFragment,
            bundle
        )
    }

    @SuppressLint("SetTextI18n")
    override fun setUserInfoVisible(userInfo: UserInformation?) {
        binding?.apply {
            tvRoomCount.text = userInfo?.numOfRooms.toString()
            val currentDate = Date(System.currentTimeMillis())
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("ko", "KR"))
            tvDate.text = dateFormat.format(currentDate)
            tvApplicant.text = userInfo?.userName.toString()
            tvContact.text = userInfo?.contactNumber
            tvVisitAddress.text = "${userInfo?.roadAddress} ${userInfo?.detailAddress}"
        }
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

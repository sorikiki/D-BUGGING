package com.example.myapplication.presentation.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.databinding.FragmentCompanyReservationBinding
import com.example.myapplication.domain.CompanyInformation
import okhttp3.internal.http.hasBody
import org.koin.android.ext.android.inject
import org.koin.android.scope.ScopeFragment

class CompanyReservationFragment : ScopeFragment(), CompanyReservationContract.View {
    private var binding: FragmentCompanyReservationBinding? = null

    lateinit var companyItem: CompanyInformation

    override val presenter: CompanyReservationContract.Presenter by inject()

    private lateinit var bugName: String
    private lateinit var firstFoundDate: String
    private lateinit var firstFoundPlace: String
    private lateinit var wantedStartDate: String
    private lateinit var wantedEndDate: String
    private lateinit var reserveDateTime: String
    private lateinit var extraMessage: String
    val numOfRooms = presenter.getCurrentUserInfo().numOfRooms
    val userId = presenter.getCurrentUserInfo().userId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCompanyReservationBinding.inflate(inflater, container, false)
        .also {
            binding = it
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        companyItem = arguments?.getParcelable("company")!!

    }


    private fun bindViews() {
        /*binding?.btnSubmit?.setOnClickListener {
            findNavController().navigate(R.id.action_companyReservationFragment_to_companyReservationCheckFragment)
        }*/

        binding?.apply {

            btnSubmit.setOnClickListener {
                bugName = etBug.text.toString()
                firstFoundDate = etFindDate.text.toString()
                firstFoundPlace = etFindSpot.text.toString()
                wantedStartDate = ""
                wantedEndDate = ""
                extraMessage = etExtraMessage.text.toString()

                if (bugName.isBlank() && firstFoundDate.isBlank() && firstFoundPlace.isBlank()) {
                    Toast.makeText(context, "필수 입력 내용을 모두 작성해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    succeedReservation(
                        bugName,
                        firstFoundDate,
                        firstFoundPlace,
                        wantedStartDate,
                        wantedEndDate,
                        reserveDateTime,
                        extraMessage
                    )
                }
            }
        }
    }

    /*
    //radiobuttonclick리스너 사용하기
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            //버튼이 체크되었는지 확인
            val checked = view.isChecked

            //어떤 버튼이 체크되었는지 확인
            when (view.getId()) {
                R.id.rb_yes ->
                    if (checked) {
                        hasBugBeenShown = true
                    }
                R.id.rb_no ->
                    if (checked) {
                        hasBugBeenShown = false
                    }
            }
        }
    }
     */

    fun succeedReservation(
        bugName: String,
        firstFoundDate: String,
        firstFoundPlace: String,
        wantedStartDate: String,
        wantedEndDate: String,
        reserveDateTime: String,
        extraMessage: String? = null
    ) {
        presenter.makeReservation(
            ReservationInfo(
                userId = userId!!,
                companyId = companyItem.companyId!!,
                bugName = bugName,
                firstFoundDate = firstFoundDate,
                firstFoundPlace = firstFoundPlace,
                wantedStartDate = wantedStartDate,
                wantedEndDate = wantedEndDate,
                reserveDateTime = reserveDateTime,
                availableVisitTime = "",
                extraMessage = extraMessage
            )
        )
    }

    override fun processReservationSuccess(reservationId: Int) {
        val bundle = bundleOf("reservation" to reservationId);
        view?.findNavController()?.navigate(
            R.id.action_companyReservationCheckFragment_to_companyReservationCompletedFragment,
            bundle
        )
    }

    override fun showLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
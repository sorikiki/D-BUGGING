package com.example.myapplication.presentation.company

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.RadioButton
import android.widget.RadioGroup
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
    private var hasBugBeenShown = false
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
        binding?.apply {
            radiogroup.setOnCheckedChangeListener { group: RadioGroup?, checkedId: Int ->
                when (group?.id) {
                    R.id.radiogroup ->
                        when(checkedId) {
                            R.id.rb_yes -> hasBugBeenShown = true
                            R.id.rb_no -> hasBugBeenShown = false
                        }
                }
            }

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
                        hasBugBeenShown,
                        wantedStartDate,
                        wantedEndDate,
                        reserveDateTime,
                        extraMessage
                    )
                }
            }
        }
    }

    fun succeedReservation(
        bugName: String,
        firstFoundDate: String,
        firstFoundPlace: String,
        hasBugBeenShown: Boolean,
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
                hasBugBeenShown = hasBugBeenShown,
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
package com.example.myapplication.presentation.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCompanyReservationBinding
import org.koin.android.ext.android.inject
import org.koin.android.scope.ScopeFragment

class CompanyReservationFragment: ScopeFragment(), CompanyReservationContract.View {
    private var binding: FragmentCompanyReservationBinding? = null

    override val presenter: CompanyReservationContract.Presenter by inject()

    private lateinit var bugName: String
    private lateinit var firstFoundDate: String
    private lateinit var firstFoundPlace: String

    /*private val hasBugBeenShown by lazy {
        binding?.
    }*/

    private lateinit var wantedStartDate: String
    private lateinit var wantedEndDate: String
    private lateinit var reserveDateTime: String
    private lateinit var extraMessage: String


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

        bindViews()

        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun bindViews() {
        binding?.btnSubmit?.setOnClickListener {
            findNavController().navigate(R.id.action_companyReservationFragment_to_companyReservationCheckFragment)
        }

        binding?.apply {
            btnSubmit.setOnClickListener {
                bugName = etBug.text.toString()
                firstFoundDate = etFindDate.text.toString()
                firstFoundPlace = etFindSpot.text.toString()

            }
        }
    }

    override fun processReservationSuccess() {
        view?.findNavController()?.navigate(R.id.action_companyReservationCheckFragment_to_companyReservationCompletedFragment)
    }

    override fun showLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingIndicator() {
        TODO("Not yet implemented")
    }
}
package com.example.myapplication.presentation.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCompanyReservationBinding

class CompanyReservationFragment: Fragment() {
    private var binding: FragmentCompanyReservationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCompanyReservationBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun bindViews() {
        binding?.btnSubmit?.setOnClickListener {
            findNavController().navigate(R.id.action_companyReservationFragment_to_companyReservationCheckFragment)
        }
    }
}
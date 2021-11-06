package com.example.myapplication.presentation.company

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCompanyItemBinding
import com.example.myapplication.domain.CompanyInformation

class CompanyItemFragment: Fragment() {
    private var binding: FragmentCompanyItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCompanyItemBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
    }

    private fun initViews() {
        val companyItem = arguments?.getParcelable<CompanyInformation>("company")
        binding?.apply {
            tvCompanyName.text = companyItem?.companyName
            tvDescription.text = companyItem?.description
            tvAvailableAreas.text = companyItem?.availableArea
            tvAvailableCounselTime.text = companyItem?.availableCounselTime
            tvContactNumbers.text = companyItem?.contactNumber

            Glide.with(root)
                .load(companyItem?.thumbNail)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivCompanyThumb)
        }
    }

    private fun bindViews() {
        binding?.btReservation?.setOnClickListener {
            findNavController().navigate(R.id.action_companyItemFragment_to_companyReservationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}
package com.example.myapplication.presentation.company

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }

    private fun initViews() {
        val companyItem = arguments?.getParcelable<CompanyInformation>("company")
        Log.d("CompanyItemFragment", companyItem.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}
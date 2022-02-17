package com.example.myapplication.presentation.company

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCompanyReservationCompletedBinding
import com.example.myapplication.presentation.home.HomeActivity

class CompanyReservationCompletedFragment: Fragment() {
    private var binding: FragmentCompanyReservationCompletedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCompanyReservationCompletedBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindViews()
    }

    private fun bindViews() {
        binding?.btnHome?.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.myapplication.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.api.ReservationInfo
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.databinding.FragmentMypageCompanyDetailListBinding
import com.example.myapplication.domain.ReservationInformation
import com.example.myapplication.domain.UserInformation
import com.example.myapplication.ext.LinearLayoutSpacingDecoration

class MyPageCompanyDetailListFragment: Fragment() {
    private var binding: FragmentMypageCompanyDetailListBinding? = null

    lateinit var userInfo: UserInformation

    private val listAdapter = MyPageCompanyDetailListAdapter { ReservationInfo ->
        val bundle = bundleOf("reservationInfo" to ReservationInfo, "userInfo" to userInfo)
        findNavController().navigate(R.id.action_myPageCompanyDetailListFragment_to_myPageCompanyDetailItemFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMypageCompanyDetailListBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val reservationList = arguments?.getParcelableArrayList<ReservationInformation>("reservationList")
        userInfo = arguments?.getParcelable("userInfo")!!
        binding?.tvUserName?.text = userInfo.userName

        binding?.recyclerView?.apply {
            adapter = listAdapter
            addItemDecoration(LinearLayoutSpacingDecoration())
        }
        listAdapter.submitList(reservationList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
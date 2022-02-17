package com.example.myapplication.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMypageProductInterestListBinding
import com.example.myapplication.domain.ProductInformation
import com.example.myapplication.ext.GridLayoutSpacingDecoration

class MyPageProductInterestListFragment: Fragment() {
    private var binding: FragmentMypageProductInterestListBinding? = null

    private val listAdapter = MyPageProductInterestListAdapter { productInformation->
        val bundle = bundleOf("product" to productInformation)
        findNavController().navigate(R.id.action_myPageProductInterestListFragment_to_productItemFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMypageProductInterestListBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val productList = arguments?.getParcelableArrayList<ProductInformation>("productList")
        val userName = arguments?.getString("userName")
        binding?.tvUserName?.text = userName
        binding?.recyclerView?.apply {
            adapter = listAdapter
            addItemDecoration(GridLayoutSpacingDecoration())
        }
        listAdapter.submitList(productList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}


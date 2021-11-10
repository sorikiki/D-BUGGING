package com.example.myapplication.presentation.bug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBugItemBinding
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.domain.CompanyInformation

class BugItemFragment: Fragment() {
    private var binding: FragmentBugItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBugItemBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
    }

    private fun initViews() {
        val bugItem = arguments?.getParcelable<BugInformation>("bug")
        binding?.apply {
            tvBugName.text = bugItem?.bugName
        }
    }

    private fun bindViews() {
        binding?.closeBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_bugItemFragment_to_bugListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
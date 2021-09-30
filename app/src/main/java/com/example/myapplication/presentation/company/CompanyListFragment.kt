package com.example.myapplication.presentation.company

import CompanyListAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCompanyListBinding
import java.util.ArrayList

class CompanyListFragment: Fragment() {

    private var binding: FragmentCompanyListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View = FragmentCompanyListBinding.inflate(inflater, container, false)
        .also {
            binding = it
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding?.rvCompanyList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CompanyListAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
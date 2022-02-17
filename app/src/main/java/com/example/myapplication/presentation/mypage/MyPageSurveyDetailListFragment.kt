package com.example.myapplication.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMypageSurveyDetailListBinding
import com.example.myapplication.domain.SurveyInformation
import com.example.myapplication.ext.GridLayoutSpacingDecoration

class MyPageSurveyDetailListFragment : Fragment() {
    private var binding: FragmentMypageSurveyDetailListBinding? = null

    private val listAdapter = MyPageSurveyDetailListAdapter { bugId ->
        val bundle = bundleOf("bugId" to bugId, "isAddingSurveyItem" to false)
        findNavController().navigate(
            R.id.action_myPageSurveyDetailListFragment_to_surveyResultFragment,
            bundle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMypageSurveyDetailListBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val surveyList = arguments?.getParcelableArrayList<SurveyInformation>("surveyList")
        val userName = arguments?.getString("userName")
        binding?.tvUserName?.text = userName
        binding?.recyclerView?.apply {
            adapter = listAdapter
            addItemDecoration(GridLayoutSpacingDecoration())
        }
        listAdapter.submitList(surveyList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}

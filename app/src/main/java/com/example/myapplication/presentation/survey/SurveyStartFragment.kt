package com.example.myapplication.presentation.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSurveyStartBinding

class SurveyStartFragment: Fragment() {
    private var binding: FragmentSurveyStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSurveyStartBinding.inflate(inflater, container, false)
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
        binding?.btnStart?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_surveyStartFragment_to_surveyFragment)
        }
    }
}
package com.example.myapplication.presentation.survey

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSurveyResultBinding
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.presentation.bug.BugSearchAdapter
import org.koin.android.scope.ScopeFragment

class SurveyResultFragment : ScopeFragment(), SurveyResultContract.View {
    private var binding: FragmentSurveyResultBinding? = null

    override val presenter: SurveyResultContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSurveyResultBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        presenter.onViewCreated()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        val userName = presenter.setCurrentUserName()
        binding?.tvTitle1?.text = "$userName 님이 목격하신"

    }

    @SuppressLint("SetTextI18n")
    override fun showBugInfo(bugInformation: BugInformation?) {
        binding?.apply {
            val bugName = bugInformation?.bugName
            tvBugName?.text = bugName
            tvAppearance?.text = bugInformation?.appearance
            tvColor?.text = bugInformation?.color
            tvBugHabitat?.text = bugInformation?.habitat
            tvBugMovement?.text = bugInformation?.movement
        }
    }


    override fun showLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
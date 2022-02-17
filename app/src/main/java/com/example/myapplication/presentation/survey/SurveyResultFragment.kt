package com.example.myapplication.presentation.survey

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSurveyResultBinding
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.ext.dpToPx
import com.example.myapplication.ext.setDrawablePadding
import com.example.myapplication.ext.splitIntoSentenceList
import com.example.myapplication.ext.toDrawable
import com.example.myapplication.presentation.bug.BugActivity
import com.example.myapplication.presentation.home.HomeActivity
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
        bindViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        val userName = presenter.setCurrentUserName()
        binding?.tvTitle1?.text = "$userName 님이 목격하신"

        val bugId = arguments?.getInt("bugId")
        val isAddingSurveyItem = arguments?.getBoolean("isAddingSurveyItem")

        presenter.getBugInformation(bugId!!, isAddingSurveyItem!!)
    }

    override fun showBugResult(bugInformation: BugInformation) {
        binding?.tvBugName?.text = bugInformation.bugName

        bugInformation.toDrawable().let {
            binding?.ivBugThumb?.setImageResource(it)

        }
        bugInformation.setDrawablePadding()
            .let { dp -> context?.let { context -> dpToPx(context, dp) } }
            ?.let { px ->
                binding?.ivBugThumb?.setPadding(
                    px
                )
            }

        for (sentence in splitIntoSentenceList(bugInformation.surveyResult)!!) {
            val tvBugTrait = TextView(context)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            tvBugTrait.apply {
                text = sentence
                textSize = 15F
                setTextColor(resources.getColor(R.color.greyish_brown, null))
                setPadding(
                    dpToPx(context, 0F),
                    dpToPx(context, 0F),
                    dpToPx(context, 0F),
                    dpToPx(context, 8F)
                )
                layoutParams = params
            }

            binding?.bugDescriptionLinearContainer?.addView(tvBugTrait)
        }
    }

    private fun bindViews() {
        binding?.btnBugMenu?.setOnClickListener {
            val intent = Intent(context, BugActivity::class.java)
            startActivity(intent)
        }

        binding?.btnHomeMenu?.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    override fun showLoadingIndicator() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.contentContainer?.visibility = View.GONE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.visibility = View.GONE
        binding?.contentContainer?.visibility = View.VISIBLE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }

}
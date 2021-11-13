package com.example.myapplication.presentation.bug

import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.data.api.response.BugItem
import com.example.myapplication.databinding.FragmentBugItemBinding
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.ext.*

class BugItemFragment : Fragment() {
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
            tvBugAppearance.text = splitIntoWords(bugItem?.appearance)?.joinToString(", ")
            tvBugHabitat.text = splitIntoWords(bugItem?.habitat)?.joinToString(", ")
            tvBugMovement.text = splitIntoWords(bugItem?.movement)?.joinToString(", ")
            tvBugColor.text = splitIntoWords(bugItem?.color)?.joinToString(", ")

            bugItem?.toDrawable().let {
                if (it != null) {
                    ivBugThumb.setImageResource(it)
                }
            }
            bugItem?.setDrawablePadding()
                ?.let { dp -> context?.let { context -> dpToPx(context, dp) } }
                ?.let { px ->
                    ivBugThumb.setPadding(
                        px
                    )
                }

            for (sentence in splitIntoSentenceList(bugItem?.surveyResult)!!) {
                val tvBugTrait = TextView(context)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                tvBugTrait.apply {
                    text = sentence
                    textSize = 14F
                    setTextColor(resources.getColor(R.color.greyish_brown, null))
                    setPadding(dpToPx(context, 0F), dpToPx(context, 0F), dpToPx(context, 0F), dpToPx(context, 8F))
                    layoutParams = params
                }

                traitDescriptionScrollView.addView(tvBugTrait)
            }
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
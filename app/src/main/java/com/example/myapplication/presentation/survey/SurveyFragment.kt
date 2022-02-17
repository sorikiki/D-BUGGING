package com.example.myapplication.presentation.survey

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSurveyBinding
import com.example.myapplication.ext.colorItem
import com.example.myapplication.ext.conditionItem
import com.example.myapplication.ext.movementItem
import com.example.myapplication.ext.traitItem

class SurveyFragment : Fragment() {
    private var binding: FragmentSurveyBinding? = null
    private val listAdapter = SurveyListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSurveyBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        binding?.viewPager?.apply {
            adapter = listAdapter
            binding?.viewPager?.isUserInputEnabled = false
            addItemDecoration(GridLayoutSpacingDecoration())
        }

        colorItem.keyWords?.forEach { item ->
            item.isSelected = false
        }

        movementItem.keyWords?.forEach { item ->
            item.isSelected = false
        }

        traitItem.keyWords?.forEach { item ->
            item.isSelected = false
        }

        conditionItem.keyWords?.forEach { item ->
            item.isSelected = false
        }

        listAdapter.submitList(listOf(colorItem, movementItem, traitItem, conditionItem))
    }

    private fun bindViews() {
        binding?.btnNext?.setOnClickListener {

            binding?.viewPager?.apply {
                if (currentItem == 3) {
                    // 랜덤으로 벌레 정보 가져오기
                    val bundle = bundleOf("bugId" to (1..8).random(), "isAddingSurveyItem" to true)
                    view?.findNavController()
                        ?.navigate(R.id.action_surveyFragment_to_surveyResultFragment, bundle)
                }

                if (currentItem != 3) {
                    currentItem += 1
                    binding?.btnNext?.setBackgroundResource(R.drawable.next_button_bg)
                    binding?.btnNext?.text = "다음"

                    when (currentItem) {
                        1 -> {
                            binding?.ivFirstDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivSecondDot?.setBackgroundResource(R.drawable.dot_green_bg)
                            binding?.ivThirdDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivFourthDot?.setBackgroundResource(R.drawable.dot_bg)
                        }
                        2 -> {
                            binding?.ivFirstDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivSecondDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivThirdDot?.setBackgroundResource(R.drawable.dot_green_bg)
                            binding?.ivFourthDot?.setBackgroundResource(R.drawable.dot_bg)
                        }
                        3 -> {
                            binding?.ivFirstDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivSecondDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivThirdDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivFourthDot?.setBackgroundResource(R.drawable.dot_green_bg)
                            binding?.btnNext?.setBackgroundResource(R.drawable.submit_button_bg)
                            binding?.btnNext?.text = "제출"
                        }
                    }
                }
            }
        }

        binding?.btnPrior?.setOnClickListener {
            binding?.viewPager?.apply {
                if (currentItem != 0) {
                    currentItem -= 1
                    when (currentItem) {
                        0 -> {
                            binding?.ivFirstDot?.setBackgroundResource(R.drawable.dot_green_bg)
                            binding?.ivSecondDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivThirdDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivFourthDot?.setBackgroundResource(R.drawable.dot_bg)
                        }
                        1 -> {
                            binding?.ivFirstDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivSecondDot?.setBackgroundResource(R.drawable.dot_green_bg)
                            binding?.ivThirdDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivFourthDot?.setBackgroundResource(R.drawable.dot_bg)
                        }
                        2 -> {
                            binding?.ivFirstDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivSecondDot?.setBackgroundResource(R.drawable.dot_bg)
                            binding?.ivThirdDot?.setBackgroundResource(R.drawable.dot_green_bg)
                            binding?.ivFourthDot?.setBackgroundResource(R.drawable.dot_bg)
                        }
                    }
                }
                if (currentItem != 3) {
                    binding?.btnNext?.setBackgroundResource(R.drawable.next_button_bg)
                    binding?.btnNext?.text = "다음"
                }
                if (currentItem == 3) {
                    binding?.btnNext?.setBackgroundResource(R.drawable.submit_button_bg)
                    binding?.btnNext?.text = "제출"
                }


            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}

private class GridLayoutSpacingDecoration() : RecyclerView.ItemDecoration() {
    private val halfSpace = 24;

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.clipToPadding = true

        outRect.top = halfSpace
        outRect.bottom = halfSpace
        outRect.left = halfSpace
        outRect.right = halfSpace
    }
}
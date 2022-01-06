package com.example.myapplication.presentation.survey

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentSurveyBinding
import com.example.myapplication.ext.colorItem
import com.example.myapplication.ext.conditionItem
import com.example.myapplication.ext.movementItem
import com.example.myapplication.ext.traitItem

class SurveyFragment : Fragment() {
    private var binding: FragmentSurveyBinding? = null
    private val listAdapter = SurveyListAdapter()
    private var currentNumber = 1


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
        listAdapter.submitList(listOf(colorItem, movementItem, traitItem, conditionItem))
    }

    @SuppressLint("ResourceAsColor")
    private fun bindViews() {
        binding?.btnNext?.setOnClickListener {
            if (currentNumber == 1) {

                binding?.viewPager?.currentItem = currentNumber+1
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
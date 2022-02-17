package com.example.myapplication.presentation.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemMypageSurveyDetailBinding
import com.example.myapplication.domain.SurveyInformation
import com.example.myapplication.ext.bugIdToDrawable
import com.example.myapplication.ext.dateTimeFormatter
import com.example.myapplication.ext.newDateFormatter
import com.example.myapplication.ext.newDateTimeFormatter

class MyPageSurveyDetailListAdapter(private val onClickListener: (Int) -> Unit) :
    ListAdapter<SurveyInformation, MyPageSurveyDetailListAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageSurveyDetailListAdapter.ViewHolder = ViewHolder(
        ItemMypageSurveyDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyPageSurveyDetailListAdapter.ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemMypageSurveyDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SurveyInformation) {
            binding.apply {
                tvBugName.text = item.bugName
                tvSurveyDate.text = newDateFormatter.format(dateTimeFormatter.parse(item.surveyDate!!)!!)
                ivBugBg.setImageResource(bugIdToDrawable(item.bugId!!))
                root.setOnClickListener {
                    onClickListener(item.bugId)
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SurveyInformation>() {
            override fun areItemsTheSame(
                oldItem: SurveyInformation,
                newItem: SurveyInformation
            ): Boolean {
                return oldItem.surveyId == newItem.surveyId
            }

            override fun areContentsTheSame(
                oldItem: SurveyInformation,
                newItem: SurveyInformation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
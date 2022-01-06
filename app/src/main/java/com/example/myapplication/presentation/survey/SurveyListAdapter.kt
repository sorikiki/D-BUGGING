package com.example.myapplication.presentation.survey

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.SurveyListViewBinding
import com.example.myapplication.ext.SurveyItem

class SurveyListAdapter : ListAdapter<SurveyItem, SurveyListAdapter.ViewHolder>(diffUtil) {

    val adapter = SurveyItemAdapter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            SurveyListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: SurveyListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SurveyItem) {
            binding.recyclerView.adapter = adapter
            adapter.submitList(item.keyWords)
            adapter.notifyDataSetChanged()
            binding.tvSurveyTitle.text = item.question

        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SurveyItem>() {
            override fun areItemsTheSame(
                oldItem: SurveyItem,
                newItem: SurveyItem
            ): Boolean {
                return oldItem.question == newItem.question
            }

            override fun areContentsTheSame(
                oldItem: SurveyItem,
                newItem: SurveyItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

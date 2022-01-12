package com.example.myapplication.presentation.survey

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.SurveyItemViewBinding
import com.example.myapplication.ext.Trait


class SurveyItemAdapter : ListAdapter<Trait, SurveyItemAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        SurveyItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SurveyItemAdapter.ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: SurveyItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Trait) {
            binding.btnSurveyItem.text = item.query
            if (item.isSelected == true) {
                binding.btnSurveyItem.apply {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.turquoise_green))
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                }

            } else {
                binding.btnSurveyItem.apply {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.light_grey))
                    setTextColor(ContextCompat.getColor(context, R.color.greyish_brown))
                }
            }

            binding.btnSurveyItem.setOnClickListener {
                Log.d("SurveyItemAdapter", item.isSelected.toString())
                if (item.isSelected == true) {
                    item.isSelected = false
                    binding.btnSurveyItem.apply {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.light_grey))
                        setTextColor(ContextCompat.getColor(context, R.color.greyish_brown))
                    }
                } else {
                    item.isSelected = true
                    binding.btnSurveyItem.apply {
                        setBackgroundColor(ContextCompat.getColor(context, R.color.turquoise_green))
                        setTextColor(ContextCompat.getColor(context, R.color.white))
                    }
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Trait>() {
            override fun areItemsTheSame(
                oldItem: Trait,
                newItem: Trait
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Trait,
                newItem: Trait
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
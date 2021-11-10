package com.example.myapplication.presentation.bug

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBugSearchBinding
import com.example.myapplication.domain.BugInformation

class BugSearchAdapter:
    ListAdapter<BugInformation, BugSearchAdapter.ViewHolder>(diffUtil) {

    var onItemClickListener: ((BugInformation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemBugSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemBugSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BugInformation) {

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(currentList[adapterPosition])
            }

            binding.searchedBugItem.text = item.bugName;
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BugInformation>() {
            override fun areItemsTheSame(
                oldItem: BugInformation,
                newItem: BugInformation
            ): Boolean {
                return oldItem.bugId == newItem.bugId
            }

            override fun areContentsTheSame(
                oldItem: BugInformation,
                newItem: BugInformation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
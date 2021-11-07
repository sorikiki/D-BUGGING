package com.example.myapplication.presentation.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemProductBinding
import com.example.myapplication.domain.ProductInformation

class ProductListAdapter :
    ListAdapter<ProductInformation, ProductListAdapter.ViewHolder>(diffUtil) {

    var onItemClickListener: ((ProductInformation) -> Unit)? = null
    var onFavoriteClickListener: ((ProductInformation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductInformation) {

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(currentList[adapterPosition])
            }

            binding.ibInterest.setOnClickListener {
                onFavoriteClickListener?.invoke(currentList[adapterPosition])
            }

            Glide.with(binding.root)
                .load(item.thumbnail)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivProductThumb)

            binding.tvProductTitle.text = item.productName
            binding.tvProductIntro.text = item.shortIntro

            if (item.isProductInterested == true) {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_fill)
            } else {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_line)
            }

            if (adapterPosition % 2 == 0) {
                binding.root.setBackgroundResource(R.color.light_grey)
            } else {
                binding.root.setBackgroundResource(R.color.white)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductInformation>() {
            override fun areItemsTheSame(
                oldItem: ProductInformation,
                newItem: ProductInformation
            ): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(
                oldItem: ProductInformation,
                newItem: ProductInformation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
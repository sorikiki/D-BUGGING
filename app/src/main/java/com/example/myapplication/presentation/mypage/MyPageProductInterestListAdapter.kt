package com.example.myapplication.presentation.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.myapplication.data.api.response.ProductItem
import com.example.myapplication.databinding.ItemMypageProductDetailBinding
import com.example.myapplication.domain.ProductInformation
import kotlinx.coroutines.withContext

class MyPageProductInterestListAdapter(private val onClick: (ProductInformation) -> Unit)
    : ListAdapter<ProductInformation, MyPageProductInterestListAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageProductInterestListAdapter.ViewHolder =
        ViewHolder(
            ItemMypageProductDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: MyPageProductInterestListAdapter.ViewHolder,
        position: Int
    ) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemMypageProductDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductInformation) {
            binding.apply {
                tvProductName.text = item.productName

                Glide.with(root)
                    .load(item.thumbnail)
                    .transition(withCrossFade())
                    .into(ivProductBg)


                tvNumOfInterests.text = item.interestNum.toString()

                root.setOnClickListener {
                    onClick(item)
                }
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
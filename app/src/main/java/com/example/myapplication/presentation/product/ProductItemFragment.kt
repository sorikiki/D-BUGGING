package com.example.myapplication.presentation.product

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginEnd
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductItemBinding
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.domain.ProductInformation
import com.example.myapplication.ext.dpToPx
import com.example.myapplication.ext.splitIntoWords
import com.example.myapplication.presentation.company.CompanyListContract
import org.koin.android.scope.ScopeFragment

class ProductItemFragment : ScopeFragment(), ProductItemContract.View {

    var productItem: ProductInformation? = null

    var totalNumberOfFavorites: Int = 0
    var isProductInterested: Boolean = false

    private var binding: FragmentProductItemBinding? = null

    override val presenter: ProductItemContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentProductItemBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        productItem = arguments?.getParcelable<ProductInformation>("product")
        binding?.apply {
            Glide.with(root)
                .load(productItem?.thumbnail)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivProductThumb)

            tvProductTitle.text = productItem?.productName

            // 상품 전체 찜하기 수
            totalNumberOfFavorites = productItem?.interestNum!!
            tvInterest.text = totalNumberOfFavorites.toString()

            // 현재 유저의 상품에 대한 찜하기 여부
            isProductInterested = productItem?.isProductInterested!!
            if (isProductInterested) {
                ivInterest.setImageResource(R.drawable.ic_heart_fill)
            } else {
                ivInterest.setImageResource(R.drawable.ic_heart_line)
            }
        }

        val tagList = splitIntoWords(productItem?.tag)
        if (tagList != null) {
            for (tag in tagList) {
                makeTagButton(requireContext(), tag)
            }
        }

        val descriptionList = splitIntoWords(productItem?.productDescription)
        if (descriptionList != null) {
            for (description in descriptionList) {
                makeTextView(requireContext(), description)
            }
        }
    }

    private fun bindViews() {
        binding?.ivInterest?.setOnClickListener {
            presenter.updateProductFavorite(productItem?.productId!!) // 이때 total_num도 바뀌고 is_product_interested도 바뀜
        }
    }

    override fun toggleFavoriteView() {
        binding?.apply {
            if (isProductInterested) {
                if (totalNumberOfFavorites != 0) {
                    totalNumberOfFavorites -= 1
                }
                ivInterest.setImageResource(R.drawable.ic_heart_line)
            } else {
                totalNumberOfFavorites += 1
                ivInterest.setImageResource(R.drawable.ic_heart_fill)
            }
            tvInterest.text = totalNumberOfFavorites.toString()
        }
        isProductInterested = !isProductInterested
    }

    private fun makeTagButton(context: Context, tag: String) {
        val tagButton = AppCompatButton(context)
        val tagName = "#$tag"
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            dpToPx(context, 34F)
        )
        params.setMargins(
            dpToPx(context, 0F),
            dpToPx(context, 0F),
            dpToPx(context, 12F),
            dpToPx(context, 12F)
        )

        tagButton.apply {
            text = tagName
            textSize = 14F
            setTextColor(resources.getColor(R.color.grey, null))
            setPadding(
                dpToPx(context, 18F),
                dpToPx(context, 0F),
                dpToPx(context, 18F),
                dpToPx(context, 0F)
            )
            layoutParams = params
            background = ResourcesCompat.getDrawable(resources, R.drawable.tag_bg, null)
        }

        binding?.tagContainer?.addView(tagButton)
    }

    private fun makeTextView(context: Context, description: String) {
        val tvDescription = TextView(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(
            dpToPx(context, 0F),
            dpToPx(context, 0F),
            dpToPx(context, 0F),
            dpToPx(context, 12F)
        )

        tvDescription.apply {
            text = "· $description"
            textSize = 16F
            setTextColor(resources.getColor(R.color.greyish_brown, null))
            layoutParams = params
        }

        binding?.productDescriptionContainer?.addView(tvDescription)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
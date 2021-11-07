package com.example.myapplication.presentation.product

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductSearchBinding
import com.example.myapplication.domain.ProductInformation
import org.koin.android.scope.ScopeFragment

class ProductSearchFragment: ScopeFragment(), ProductSearchContract.View {

    override val presenter: ProductSearchContract.Presenter by inject()

    private val productListAdapter = ProductListAdapter()

    private var binding: FragmentProductSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View = FragmentProductSearchBinding.inflate(inflater, container, false)
        .also {
            binding = it
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()

        presenter.onViewCreated()
    }

    private fun initViews() {
        binding?.rvProductList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productListAdapter
        }
    }

    private fun bindViews() {
        binding?.searchEditTextView?.addTextChangedListener { editable ->
            presenter.filterProducts(editable.toString())
        }

        binding?.searchBarIcon?.setOnClickListener {
            hideKeyBoard()
        }

        productListAdapter.onItemClickListener = { productInformation ->
            val bundle = bundleOf("product" to productInformation)
            view?.findNavController()?.navigate(R.id.action_productSearchFragment_to_productItemFragment, bundle)
        }

        productListAdapter.onFavoriteClickListener = { productInformation ->
            presenter.updateProductFavorite(productInformation.productId!!)
        }
    }

    override fun showProductItems(items: List<ProductInformation>) {
        productListAdapter.apply {
            submitList(items)
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}
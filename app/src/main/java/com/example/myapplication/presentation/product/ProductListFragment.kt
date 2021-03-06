package com.example.myapplication.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductListBinding
import com.example.myapplication.domain.CompanyInformation
import com.example.myapplication.domain.ProductInformation
import com.example.myapplication.presentation.company.CompanyListContract
import org.koin.android.ext.android.inject
import org.koin.android.scope.ScopeFragment
import java.util.ArrayList

class ProductListFragment : ScopeFragment(), ProductListContract.View {

    private var binding: FragmentProductListBinding? = null

    private val productListAdapter = ProductListAdapter()

    override val presenter: ProductListContract.Presenter by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentProductListBinding.inflate(inflater, container, false)
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
        productListAdapter.onItemClickListener = { productInformation ->
            val bundle = bundleOf("product" to productInformation)
            view?.findNavController()?.navigate(R.id.action_productListFragment_to_productItemFragment, bundle)
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


    override fun showErrorMessage() {
        binding?.progressBar?.visibility = View.GONE
        binding?.contentContainer?.visibility = View.GONE
        binding?.errorMessage?.visibility = View.VISIBLE
    }


    override fun showLoadingIndicator() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.contentContainer?.visibility = View.GONE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.visibility = View.GONE
        binding?.contentContainer?.visibility = View.VISIBLE
        binding?.errorMessage?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }
}
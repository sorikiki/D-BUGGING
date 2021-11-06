package com.example.myapplication.presentation.company

import CompanyListAdapter
import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCompanySearchBinding
import com.example.myapplication.domain.CompanyInformation
import org.koin.android.scope.ScopeFragment

class CompanySearchFragment : ScopeFragment(), CompanySearchContract.View {

    override val presenter: CompanySearchContract.Presenter by inject()

    private val companyListAdapter = CompanyListAdapter()

    private var binding: FragmentCompanySearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View = FragmentCompanySearchBinding.inflate(inflater, container, false)
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
        binding?.rvCompanyList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = companyListAdapter
        }
    }

    private fun bindViews() {
        binding?.searchEditTextView?.addTextChangedListener { editable ->
            presenter.filterStations(editable.toString())
        }

        binding?.searchBarIcon?.setOnClickListener {
            hideKeyBoard()
        }

        companyListAdapter.onItemClickListener = { companyInformation ->
            val bundle = bundleOf("company" to companyInformation)
            view?.findNavController()?.navigate(R.id.action_companySearchFragment_to_companyItemFragment, bundle)
        }

        companyListAdapter.onFavoriteClickListener = { companyInformation ->
            presenter.updateCompanyFavorite(companyInformation.companyId!!)
        }
    }

    override fun showCompanyItems(items: List<CompanyInformation>) {
        companyListAdapter.apply {
            submitList(items)
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}
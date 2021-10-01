package com.example.myapplication.presentation.company

import CompanyListAdapter
import android.content.Context
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
import com.example.myapplication.databinding.FragmentCompanyListBinding
import com.example.myapplication.domain.CompanyInformation
import org.koin.android.scope.ScopeFragment
import java.util.ArrayList

class CompanyListFragment : ScopeFragment(), CompanyListContract.View {

    private var binding: FragmentCompanyListBinding? = null

    private val companyListAdapter = CompanyListAdapter()

    override val presenter: CompanyListContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View = FragmentCompanyListBinding.inflate(inflater, container, false)
        .also {
            binding = it
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()

        initViews()
    }

    private fun initViews() {
        binding?.rvCompanyList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = companyListAdapter
        }

        companyListAdapter.onItemClickListener = { companyInformation ->
            val bundle = bundleOf("company" to companyInformation)
            view?.findNavController()?.navigate(R.id.action_companyListFragment_to_companyItemFragment, bundle)
        }

    }

    override fun showCompanyItems(items: List<CompanyInformation>) {
        companyListAdapter.mData = items
        companyListAdapter.notifyDataSetChanged()
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
    }
}
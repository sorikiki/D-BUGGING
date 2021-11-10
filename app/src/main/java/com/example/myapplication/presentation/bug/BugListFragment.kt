package com.example.myapplication.presentation.bug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBugListBinding
import com.example.myapplication.domain.BugInformation
import org.koin.android.ext.android.inject
import org.koin.android.scope.ScopeFragment

class BugListFragment: ScopeFragment(), BugListContract.View {

    private var binding: FragmentBugListBinding? = null

    private val bugListAdapter = BugListAdapter()

    override val presenter: BugListContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBugListBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()

        presenter.onViewCreated()
    }

    private fun initViews() {
        binding?.rvBugList?.apply {
            adapter = bugListAdapter
        }
    }

    private fun bindViews() {
        bugListAdapter.onItemClickListener = { bugInformation ->
            val bundle = bundleOf("bug" to bugInformation)
            view?.findNavController()?.navigate(R.id.action_bugListFragment_to_bugItemFragment, bundle)
        }
    }

    override fun showBugItems(items: List<BugInformation>) {
        bugListAdapter.apply {
            submitList(items)
        }
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

    override fun showErrorMessage() {
        binding?.progressBar?.visibility = View.GONE
        binding?.contentContainer?.visibility = View.GONE
        binding?.errorMessage?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }
}
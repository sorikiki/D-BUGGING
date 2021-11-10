package com.example.myapplication.presentation.bug

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBugSearchBinding
import com.example.myapplication.domain.BugInformation
import org.koin.android.scope.ScopeFragment

class BugSearchFragment: ScopeFragment(), BugSearchContract.View {

    override val presenter: BugSearchContract.Presenter by inject()

    private val bugSearchAdapter = BugSearchAdapter()

    private var binding: FragmentBugSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View = FragmentBugSearchBinding.inflate(inflater, container, false)
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
        binding?.rvBugList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bugSearchAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun bindViews() {
        binding?.searchEditTextView?.addTextChangedListener { editable ->
            presenter.filterBugs(editable.toString())
        }

        binding?.searchBarIcon?.setOnClickListener {
            hideKeyBoard()
        }

        bugSearchAdapter.onItemClickListener = { bugInformation ->
            val bundle = bundleOf("bug" to bugInformation)
            view?.findNavController()?.navigate(R.id.action_bugSearchFragment_to_bugItemFragment, bundle)
        }

    }

    override fun showBugItems(items: List<BugInformation>) {
        bugSearchAdapter.apply {
            submitList(items)
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}
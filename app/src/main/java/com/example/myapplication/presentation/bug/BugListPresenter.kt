package com.example.myapplication.presentation.bug

import com.example.myapplication.data.repository.BugRepository
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.domain.ProductInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BugListPresenter(
    private val bugRepository: BugRepository,
    val view: BugListContract.View
) : BugListContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    private val bugItems: MutableStateFlow<List<BugInformation>> =
        MutableStateFlow(emptyList())

    init {
        observeBugs()
    }

    override fun onViewCreated() {
        scope.launch {
            bugRepository.refreshBugs()
            view.showBugItems(bugItems.value)
        }
    }

    private fun observeBugs() {
        bugRepository
            .bugs
            .onStart { view.showLoadingIndicator() }
            .onEach {
                if (it.isNotEmpty()) {
                    view.hideLoadingIndicator()
                }
                bugItems.value = it
                view.showBugItems(it)
            }
            .catch {
                view.hideLoadingIndicator()
                view.showErrorMessage()
            }
            .launchIn(scope)
    }

    override fun onDestroyView() {}
}
package com.example.myapplication.presentation.bug

import com.example.myapplication.data.repository.BugRepository
import com.example.myapplication.domain.BugInformation
import com.example.myapplication.domain.ProductInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BugSearchPresenter(
    private val bugRepository: BugRepository,
    private val view: BugSearchContract.View
): BugSearchContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    private val queryString: MutableStateFlow<String> = MutableStateFlow("")

    private val bugItems: MutableStateFlow<List<BugInformation>> =
        MutableStateFlow(emptyList())

    init {
        observeBugs()
    }

    override fun onViewCreated() {
        scope.launch {
            view.showBugItems(bugItems.value)
            bugRepository.refreshBugs()
        }
    }

    override fun filterBugs(query: String) {
        scope.launch {
            queryString.emit(query)
        }
    }

    private fun observeBugs() {
        bugRepository
            .bugs
            .combine(queryString) { bugs, query ->
                if (query.isBlank()) {
                    emptyList()
                } else {
                    bugs.filter { it.bugName?.contains(query) ?: false }
                }
            }
            .onEach {
                bugItems.value = it
                view.showBugItems(it)
            }
            .launchIn(scope)
    }

    override fun onDestroyView() {}
}
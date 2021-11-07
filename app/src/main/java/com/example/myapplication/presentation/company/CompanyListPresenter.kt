package com.example.myapplication.presentation.company

import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.domain.CompanyInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CompanyListPresenter(
    private val companyRepository: CompanyRepository,
    val view: CompanyListFragment
) : CompanyListContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    private val companyItems: MutableStateFlow<List<CompanyInformation>> =
        MutableStateFlow(emptyList())

    init {
        observeCompanies()
    }

    override fun onViewCreated() {
        scope.launch {
            companyRepository.refreshCompanies()
            view.showCompanyItems(companyItems.value)
        }
    }

    private fun observeCompanies() {
        companyRepository
            .companies
            .onStart { view.showLoadingIndicator() }
            .onEach {
                if (it.isNotEmpty()) {
                    view.hideLoadingIndicator()
                }
                companyItems.value = it
                view.showCompanyItems(it)
            }
            .catch {
                view.hideLoadingIndicator()
                view.showErrorMessage()
            }
            .launchIn(scope)
    }

    override fun updateCompanyFavorite(companyId: Int) {
        scope.launch {
            companyRepository.updateCompanyFavorite(companyId)
            companyRepository.updateRemoteCompanyList()
        }
    }

    override fun onDestroyView() {}

}
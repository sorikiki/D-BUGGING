package com.example.myapplication.presentation.company

import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.domain.CompanyInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CompanySearchPresenter(
    private val companyRepository: CompanyRepository,
    val view: CompanySearchFragment
) : CompanySearchContract.Presenter{

    override val scope: CoroutineScope = MainScope()

    private val queryString: MutableStateFlow<String> = MutableStateFlow("")

    private val companyItems: MutableStateFlow<List<CompanyInformation>> =
        MutableStateFlow(emptyList())

    init {
        observeCompanies()
    }

    override fun onViewCreated() {
        scope.launch {
            view.showCompanyItems(companyItems.value)
            companyRepository.refreshCompanies()
        }
    }

    override fun filterStations(query: String) {
        scope.launch {
            queryString.emit(query)
        }
    }

    private fun observeCompanies() {
        companyRepository
            .companies
            .combine(queryString) { companies, query ->
                if (query.isBlank()) {
                    emptyList()
                } else {
                    companies.filter { it.companyName?.contains(query) ?: false }
                }
            }
            .onEach {
                companyItems.value = it
                view.showCompanyItems(it)
            }
            .launchIn(scope)
    }

    override fun updateCompanyFavorite(companyId: Int) {
        scope.launch {
            companyRepository.updateCompanyFavorite(companyId)
            companyRepository.updateRemoteCompanyList()
        }
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }
}
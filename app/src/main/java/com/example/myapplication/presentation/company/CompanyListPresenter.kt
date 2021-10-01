package com.example.myapplication.presentation.company

import com.example.myapplication.data.repository.CompanyRepository
import com.example.myapplication.domain.CompanyInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CompanyListPresenter(
    private val companyRepository: CompanyRepository,
    val view: CompanyListFragment
) : CompanyListContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    private var companyItems: List<CompanyInformation>? = null

    override fun onViewCreated() {
        getCompanyList()
    }

    override fun getCompanyList() {
        scope.launch {
            view.showLoadingIndicator()
            companyItems = companyRepository.getCompanyList()
            if (companyItems == null) {
                view.showErrorMessage()
            } else {
                view.hideLoadingIndicator()
                view.showCompanyItems(companyItems!!)
            }
        }
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }
}
package com.example.myapplication.presentation.product

import com.example.myapplication.data.repository.ProductRepository
import com.example.myapplication.domain.ProductInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductListPresenter(
    private val productRepository: ProductRepository,
    val view: ProductListFragment
) : ProductListContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    private val productItems: MutableStateFlow<List<ProductInformation>> =
        MutableStateFlow(emptyList())

    init {
        observeProducts()
    }

    override fun onViewCreated() {
        scope.launch {
            productRepository.refreshProducts()
            view.showProductItems(productItems.value)
        }
    }

    private fun observeProducts() {
        productRepository
            .products
            .onStart { view.showLoadingIndicator() }
            .onEach {
                if (it.isNotEmpty()) {
                    view.hideLoadingIndicator()
                }
                productItems.value = it
                view.showProductItems(it)
            }
            .catch {
                view.hideLoadingIndicator()
                view.showErrorMessage()
            }
            .launchIn(scope)
    }

    override fun updateProductFavorite(productId: Int) {
        scope.launch {
            productRepository.updateProductFavorite(productId)
            productRepository.updateRemoteProductList()
        }
    }

    override fun onDestroyView() {}


}
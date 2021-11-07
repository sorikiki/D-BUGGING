package com.example.myapplication.presentation.product

import com.example.myapplication.data.repository.ProductRepository
import com.example.myapplication.domain.ProductInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProductSearchPresenter(
    private val productRepository: ProductRepository,
    val view: ProductSearchFragment
) : ProductSearchContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    private val queryString: MutableStateFlow<String> = MutableStateFlow("")

    private val productItems: MutableStateFlow<List<ProductInformation>> =
        MutableStateFlow(emptyList())

    init {
        observeProducts()
    }

    override fun onViewCreated() {
        scope.launch {
            view.showProductItems(productItems.value)
            productRepository.refreshProducts()
        }
    }

    override fun filterProducts(query: String) {
        scope.launch {
            queryString.emit(query)
        }
    }

    private fun observeProducts() {
        productRepository
            .products
            .combine(queryString) { products, query ->
                if (query.isBlank()) {
                    emptyList()
                } else {
                    products.filter { it.productName?.contains(query) ?: false }
                }
            }
            .onEach {
                productItems.value = it
                view.showProductItems(it)
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
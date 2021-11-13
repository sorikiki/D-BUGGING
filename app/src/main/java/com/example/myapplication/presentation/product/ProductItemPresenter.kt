package com.example.myapplication.presentation.product

import com.example.myapplication.data.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ProductItemPresenter(
    private val productRepository: ProductRepository,
    private val view: ProductItemContract.View
): ProductItemContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {}

    override fun updateProductFavorite(productId: Int) {
        scope.launch {
            productRepository.updateProductFavorite(productId)
            productRepository.updateRemoteProductList()
            view.toggleFavoriteView()
        }
    }

    override fun onDestroyView() {}
}
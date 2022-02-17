package com.example.myapplication.presentation.mypage

import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MyPageMainPresenter(
    private val userRepository: UserRepository,
    private val view: MyPageMainContract.View
): MyPageMainContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun getUserInformation() {
        view.showLoadingIndicator()

        scope.launch {
            val userInfo = userRepository.getCurrentUserInfo()

            view.hideLoadingIndicator()
            view.showUserInformation(userInfo)
        }
    }

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }
}
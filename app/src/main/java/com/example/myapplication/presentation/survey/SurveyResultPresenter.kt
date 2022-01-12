package com.example.myapplication.presentation.survey

import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.presentation.home.HomePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SurveyResultPresenter(
    private val userRepository: UserRepository,
    private val surveyResultFragment: SurveyResultFragment
) : SurveyResultContract.Presenter {
    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun setCurrentUserName(): String {
        return userRepository.getCurrentUserName()!!
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }
}
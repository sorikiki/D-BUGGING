package com.example.myapplication.presentation.survey

import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class SurveyResultPresenter(
    private val userRepository: UserRepository,
    private val surveyResultFragment: surveyResultFragment
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
package com.example.myapplication.presentation.survey

import com.example.myapplication.data.repository.BugRepository
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SurveyResultPresenter(
    private val userRepository: UserRepository,
    private val bugRepository: BugRepository,
    private val view: SurveyResultContract.View
) : SurveyResultContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun getBugInformation(bugId: Int, isAddingSurveyItem: Boolean) {
        val userId = userRepository.getPreferenceUserId()
        view.showLoadingIndicator()
        scope.launch {
            if (isAddingSurveyItem) {
                val bugInformation = bugRepository.addSurveyItem(bugId, userId!!)
                view.hideLoadingIndicator()
                view.showBugResult(bugInformation!!)
            } else {
                val bugInformation = bugRepository.getBugItem(bugId)
                view.hideLoadingIndicator()
                view.showBugResult(bugInformation!!)
            }
        }
    }

    override fun setCurrentUserName(): String {
        return userRepository.getPreferenceUserName()!!
    }

    override fun onDestroyView() {

    }
}
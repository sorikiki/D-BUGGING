package com.example.myapplication.presentation.survey

import com.example.myapplication.data.repository.BugRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.domain.BugInformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SurveyResultPresenter(
    private val userRepository: UserRepository,
    private val bugRepository: BugRepository,
    private val view: SurveyResultFragment
) : SurveyResultContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override  var currentBug : BugInformation? = null

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun setCurrentUserName(): String {
        return userRepository.getCurrentUserName()!!
    }

    override fun getBugInfo(bugId: Int, userId: String) {
        scope.launch {
            currentBug = bugRepository.getBugInfo(bugId, userId)
            view.showBugInfo(currentBug)
        }
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }
}
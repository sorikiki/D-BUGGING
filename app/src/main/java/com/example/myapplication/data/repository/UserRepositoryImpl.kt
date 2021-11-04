package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.api.UserApi
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.data.db.CompanyDao
import com.example.myapplication.data.preference.PreferenceManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val preferenceManager: PreferenceManager,
    private val companyDao: CompanyDao,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun registerUser(userInfo: UserInfo) {
        //todo userApi 의 회원가입 함수 호출
        userApi.signUpUser(userInfo)
    }

    override suspend fun processLogIn(id: String, password: String): Boolean {
        var loginSucceed = false

        userApi.signInUser(id, password)
            .body()
            .also { response ->
                Log.d("response", response.toString())
                if (response != null) {
                    val currentUserId = response.currentUser?.userId
                    val currentUserName = response.currentUser?.userName

                    if (currentUserId != null) {
                        if (currentUserName != null) {
                            preferenceManager.putUserInfo(currentUserId, currentUserName)
                        }
                    }

                    loginSucceed = response.success ?: false
                }
            }
        return loginSucceed
    }

    override fun getCurrentUserId(): String? {
        return preferenceManager.getUserId()
    }

    override fun getCurrentUserName(): String? {
        return preferenceManager.getUserName()
    }


    override suspend fun clearUser(id: String) {
        userApi.removeUser(id);
        logOutUser();
    }

    override suspend fun logOutUser() {
        preferenceManager.putUserInfo(USER_ID, USER_LOG_OFF);
        preferenceManager.putUserInfo(USER_NAME, USER_LOG_OFF);

        withContext(dispatcher) {
            companyDao.removeCompanyItems()
        }
    }

    companion object {
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
        const val USER_LOG_OFF = "LOG_OFF"
    }
}
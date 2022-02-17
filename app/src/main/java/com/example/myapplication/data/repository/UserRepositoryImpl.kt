package com.example.myapplication.data.repository

import com.example.myapplication.data.api.UserApi
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.data.api.response.mapper.toUserInformation
import com.example.myapplication.data.db.BugDao
import com.example.myapplication.data.db.CompanyDao
import com.example.myapplication.data.db.ProductDao
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.domain.UserInformation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val preferenceManager: PreferenceManager,
    private val companyDao: CompanyDao,
    private val productDao: ProductDao,
    private val bugDao: BugDao,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun registerUser(userInfo: UserInfo) {
        userApi.signUpUser(userInfo)
    }

    override suspend fun processLogIn(id: String, password: String): Boolean {
        var logInSucceed: Boolean

        userApi.signInUser(id, password)
            .body()
            ?.user
            .also { response ->
                val currentUserId = response?.userId
                val currentUserName = response?.userName

                if (currentUserId != null) {
                    if (currentUserName != null) {
                        preferenceManager.putUserInfo(currentUserId, currentUserName)
                    }
                }

                logInSucceed = true
            }

        return logInSucceed
    }

    override fun getPreferenceUserId(): String? {
        return preferenceManager.getUserId()
    }

    override fun getPreferenceUserName(): String? {
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
            productDao.removeProductItems()
            bugDao.removeBugItems()
        }
    }

    override suspend fun getCurrentUserInfo(): UserInformation? {
        return getPreferenceUserId()?.let {
            userApi.getUserInformation(it)
                .body()
                ?.userDetail
                ?.toUserInformation()
        }
    }

    companion object {
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
        const val USER_LOG_OFF = "LOG_OFF"
    }
}
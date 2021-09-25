package com.example.myapplication.data.repository

import android.util.Log
import android.view.KeyEvent
import com.example.myapplication.data.api.UserApi
import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.data.api.response.UserResponse
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.data.preference.SharedPreferenceManager
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Call
import java.sql.RowId
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun registerUser(userInfo: UserInfo) {
        //todo userApi 의 회원가입 함수 호출
        userApi.signUpUser(userInfo);
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


    override suspend fun clearUser(id: String) {
        //todo userApi 의 회원탈퇴 함수 호출
        userApi.removeUser(id);
        logOutUser();
    }

    private fun logOutUser() {
        // todo 회원탈퇴는 동시에 로그아웃을 의미하므로 이는 clearUser 함수에 종속되는 함수
        //  & 아이디와 유저이름 sharedPreference 에 각각 USER_LOG_OFF 값으로 업로드

        preferenceManager.putUserInfo(USER_ID, USER_LOG_OFF);
        preferenceManager.putUserInfo(USER_NAME, USER_LOG_OFF);

    }

    companion object {
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
        const val USER_LOG_OFF = "LOG_OFF"
    }
}
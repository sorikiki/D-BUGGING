package com.example.myapplication.data.repository

import com.example.myapplication.data.api.UserApi
import com.example.myapplication.data.preference.PreferenceManager
import kotlinx.coroutines.CoroutineDispatcher

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
): UserRepository {

    override suspend fun registerUser() {
        //todo userApi 의 회원가입 함수 호출
    }

    override suspend fun processLogIn() {
        //todo userApi 의 로그인 함수 호출 & 아이디와 유저이름 sharedPreference 에 업로드
    }

    override suspend fun clearUser() {
        //todo userApi 의 회원탈퇴 함수 호출
    }

    override suspend fun incrementNumberOfCompanyUsages() {
        //todo 업체이용량 증가 함수 호출
    }

    private fun logOutUser() {
        // todo 회원탈퇴는 동시에 로그아웃을 의미하므로 이는 clearUser 함수에 종속되는 함수
        //  & 아이디와 유저이름 sharedPreference 에 각각 USER_LOG_OFF 값으로 업로드
    }
}
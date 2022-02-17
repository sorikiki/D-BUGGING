package com.example.myapplication.data.repository

import com.example.myapplication.data.api.UserInfo
import com.example.myapplication.domain.UserInformation

interface UserRepository {
    suspend fun registerUser(userInfo: UserInfo)

    suspend fun processLogIn(id : String, password : String): Boolean

    suspend fun clearUser(id : String)

    fun getPreferenceUserId(): String?

    fun getPreferenceUserName(): String?

    suspend fun logOutUser()

    suspend fun getCurrentUserInfo(): UserInformation?
}
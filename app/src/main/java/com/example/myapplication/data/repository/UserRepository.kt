package com.example.myapplication.data.repository

import com.example.myapplication.data.api.UserInfo

interface UserRepository {
    suspend fun registerUser(userInfo: UserInfo);

    suspend fun processLogIn(id : String, password : String): Boolean

    suspend fun clearUser(id : String)

    fun getCurrentUserId(): String?

    fun getCurrentUserName(): String?

    suspend fun logOutUser()
}
package com.example.myapplication.data.preference

interface PreferenceManager {
    fun getUserId(): String?

    fun getUserName(): String?

    fun putUserInfo(userId: String, userName: String)
}
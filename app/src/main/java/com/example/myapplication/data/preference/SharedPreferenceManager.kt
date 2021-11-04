package com.example.myapplication.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferenceManager(
    private val sharedPreferences: SharedPreferences
) : PreferenceManager {

    override fun getUserId(): String? {
        val value = sharedPreferences.getString(USER_ID, USER_LOG_OFF)
        return if (value == USER_LOG_OFF) {
            null
        } else {
            value
        }
    }

    override fun getUserName(): String? {
        val value = sharedPreferences.getString(USER_NAME, USER_LOG_OFF)
        return if (value == USER_LOG_OFF) {
            null
        } else {
            value
        }
    }

    override fun putUserInfo(userId: String, userName: String) {
        sharedPreferences.edit {
            putString(USER_ID, userId)
            putString(USER_NAME, userName)
        }
    }

    companion object {
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
        const val USER_LOG_OFF = "LOG_OFF"
    }
}
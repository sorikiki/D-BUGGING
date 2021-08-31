package com.example.myapplication.di

import android.app.Activity
import com.example.myapplication.data.api.UserApi
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.data.preference.SharedPreferenceManager
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

val appModule = module {
    single { Dispatchers.IO }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Retrofit
    /* todo baseUrl 추가
    single<UserApi> {
        Retrofit.Builder().baseUrl()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
     */

}
package com.example.myapplication.di

import android.app.Activity
import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.api.CompanyApi
import com.example.myapplication.data.api.ProductApi
import com.example.myapplication.data.api.Url.BASE_URL
import com.example.myapplication.data.api.UserApi
import com.example.myapplication.data.db.AppDatabase
import com.example.myapplication.data.preference.PreferenceManager
import com.example.myapplication.data.preference.SharedPreferenceManager
import com.example.myapplication.data.repository.*
import com.example.myapplication.presentation.company.*
import com.example.myapplication.presentation.home.HomeActivity
import com.example.myapplication.presentation.home.HomeContract
import com.example.myapplication.presentation.home.HomePresenter
import com.example.myapplication.presentation.login.LoginActivity
import com.example.myapplication.presentation.login.LoginContract
import com.example.myapplication.presentation.login.LoginPresenter
import com.example.myapplication.presentation.product.*
import com.example.myapplication.presentation.register.*
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Url

val appModule = module {
    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().companyDao() }
    single { get<AppDatabase>().productDao() }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Retrofit
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }


    single<UserApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }


    single<CompanyApi> {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    single<ProductApi> {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    // Repository
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get(), get()) }
    single<CompanyRepository> { CompanyRepositoryImpl(get(), get(), get(), get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(), get(), get(), get() )}

    // Presentation
    scope<LoginActivity> {
        scoped<LoginContract.Presenter> { LoginPresenter(get(), getSource()) }
    }

    scope<RegisterFragment> {
        scoped<RegisterContract.Presenter> { RegisterPresenter(get(), getSource()) }
    }

    scope<HomeActivity> {
        scoped<HomeContract.Presenter> { HomePresenter(get(), get(), getSource())}
    }

    scope<CompanyListFragment> {
        scoped<CompanyListContract.Presenter> { CompanyListPresenter(get(), getSource()) }
    }

    scope<CompanySearchFragment> {
        scoped<CompanySearchContract.Presenter> { CompanySearchPresenter(get(), getSource()) }
    }

    scope<ProductListFragment> {
        scoped<ProductListContract.Presenter> { ProductListPresenter(get(), getSource()) }
    }

    scope<ProductSearchFragment> {
        scoped<ProductSearchContract.Presenter> { ProductSearchPresenter(get(), getSource()) }
    }
}
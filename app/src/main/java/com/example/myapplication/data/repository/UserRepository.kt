package com.example.myapplication.data.repository

interface UserRepository {
    suspend fun registerUser()

    suspend fun processLogIn()

    suspend fun clearUser()

    suspend fun incrementNumberOfCompanyUsages()
}
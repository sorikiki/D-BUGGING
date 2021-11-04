package com.example.myapplication.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {

    @Query("SELECT * FROM CompanyEntity WHERE companyId=:companyId")
    suspend fun getCompanyItem(companyId: Int): CompanyEntity

    @Query("SELECT * FROM CompanyEntity")
    fun getCompanyList(): Flow<List<CompanyEntity>>

    @Query("DELETE FROM CompanyEntity")
    fun removeCompanyItems()

    @Update
    suspend fun updateCompany(companyEntity: CompanyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyList(station: List<CompanyEntity>)
}
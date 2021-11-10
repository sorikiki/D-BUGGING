package com.example.myapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BugDao {

    @Query("SELECT * FROM BugEntity")
    fun getBugList(): Flow<List<BugEntity>>

    @Query("DELETE FROM BugEntity")
    fun removeBugItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBugList(bugs: List<BugEntity>)
}
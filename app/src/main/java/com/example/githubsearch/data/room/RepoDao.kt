package com.example.githubsearch.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert
    suspend fun insert(vararg repoDb: RepoDb)

    @Delete
    suspend fun delete(repoDb: RepoDb)

    @Query("DELETE FROM repo")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM repo ORDER BY id")
    fun getAll(): Flow<List<RepoDb>>
}
package com.example.githubsearch.data.repositories

import androidx.annotation.WorkerThread
import com.example.githubsearch.data.room.RepoDao
import com.example.githubsearch.data.room.RepoDb
import kotlinx.coroutines.flow.Flow

class RoomRepository(private val repoDao: RepoDao) {
    val getDataFromDatabase: Flow<List<RepoDb>> = repoDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(repoDb: RepoDb){
        repoDao.insert(repoDb)
    }

    suspend fun delete(repoDb: RepoDb){
        repoDao.delete(repoDb)
    }
}
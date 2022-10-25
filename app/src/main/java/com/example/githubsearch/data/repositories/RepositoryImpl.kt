package com.example.githubsearch.data.repositories

import androidx.annotation.WorkerThread
import com.example.githubsearch.data.api.ApiService
import com.example.githubsearch.data.mapper.ResponseDataMapper
import com.example.githubsearch.data.room.RepoDao
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val repoDao: RepoDao
) : Repository {
    //Retrofit
    suspend fun getSearchRepository(query: String) = ResponseDataMapper().mapResponse(
        apiService.getSearchRepository(query)
    )
    //Room
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
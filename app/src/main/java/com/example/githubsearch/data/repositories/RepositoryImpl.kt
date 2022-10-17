package com.example.githubsearch.data.repositories

import com.example.githubsearch.data.api.ApiService
import com.example.githubsearch.data.mapper.ResponseDataMapper
import com.example.githubsearch.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {
    suspend fun getSearchRepository(query: String) = ResponseDataMapper().mapResponse(
        apiService.getSearchRepository(query)
    )
}
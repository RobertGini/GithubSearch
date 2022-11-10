package com.example.githubsearch.data.repositories

import com.example.githubsearch.data.api.ApiService
import com.example.githubsearch.data.mapper.ResponseDataMapper
import com.example.githubsearch.domain.iterators.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ResponseDataMapper
) : Repository {
    //Retrofit
    suspend fun getSearchRepository(query: String) = mapper.mapResponse(
        apiService.getSearchRepository(query)
    )
}
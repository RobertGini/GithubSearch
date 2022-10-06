package com.example.githubsearch.data.repositories

import com.example.githubsearch.data.api.ApiService
import com.example.githubsearch.data.mapper.ResponseDataMapper

class ApiRepository(private val apiService: ApiService) {
    suspend fun getSearchRepository(query: String) = ResponseDataMapper().mapResponse(
        apiService.getSearchRepository(query)
    )
}
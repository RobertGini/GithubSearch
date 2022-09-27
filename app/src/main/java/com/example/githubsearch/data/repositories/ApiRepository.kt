package com.example.githubsearch.data.repositories

import com.example.githubsearch.data.api.ApiService

class ApiRepository(private val apiService: ApiService) {
    suspend fun getSearchRepository(query: String) = apiService.getSearchRepository(query)
}
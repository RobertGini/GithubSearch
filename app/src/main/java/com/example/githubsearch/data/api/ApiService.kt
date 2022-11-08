package com.example.githubsearch.data.api

import com.example.githubsearch.data.model.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    suspend fun getSearchRepository(
        @Query("q") query: String
    ): GithubResponse
}
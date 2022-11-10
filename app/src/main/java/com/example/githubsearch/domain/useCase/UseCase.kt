package com.example.githubsearch.domain.useCase

interface UseCase<S, R> {
    suspend fun getSearchRepository(params : S) : R
}


package com.example.githubsearch.domain.useCase

import androidx.lifecycle.LiveData

interface UseCaseRoom<R> {
    val getDataFromDatabase: LiveData<List<R>>

    suspend fun insert(repoDb: R)

    suspend fun delete(repoDb: R)
}
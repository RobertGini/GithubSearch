package com.example.githubsearch.data.mapper

import com.example.githubsearch.data.model.GithubResponse
import com.example.githubsearch.data.model.RepositoryResponse
import com.example.githubsearch.domain.RepoEntity
import com.example.githubsearch.domain.RepoItemsEntity
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ResponseDataMapper @Inject constructor() {
    @Singleton
    @Provides
    fun mapResponse(response: GithubResponse): RepoEntity {
        return RepoEntity(
            itemsRepo = response.items?.map { transformData(it) }.orEmpty()
        )
    }
    @Singleton
    @Provides
    fun transformData(response: RepositoryResponse): RepoItemsEntity {
        return RepoItemsEntity(
            full_name = response.full_name.toString(),
            description = response.description.toString(),
            forks = response.forks.toString(),
            created_at = response.created_at.toString()
        )
    }
}
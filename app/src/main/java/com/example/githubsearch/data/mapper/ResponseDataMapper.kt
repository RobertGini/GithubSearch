package com.example.githubsearch.data.mapper

import com.example.githubsearch.data.model.GithubResponse
import com.example.githubsearch.data.model.RepositoryResponse
import com.example.githubsearch.domain.RepoEntity
import com.example.githubsearch.domain.RepoItemsEntity

class ResponseDataMapper {

    fun mapResponse(response: GithubResponse): RepoEntity {
        return RepoEntity(
            itemsRepo = response.items?.map { transformData(it) }.orEmpty()
        )
    }
    private fun transformData(response: RepositoryResponse): RepoItemsEntity {
        return RepoItemsEntity(
            full_name = response.full_name.toString(),
            description = response.description.toString(),
            forks = response.forks.toString(),
            created_at = response.created_at.toString()
        )
    }
}
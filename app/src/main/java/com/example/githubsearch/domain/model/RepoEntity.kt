package com.example.githubsearch.domain

import kotlinx.serialization.Serializable

@Serializable
data class RepoEntity(
    val itemsRepo: List<RepoItemsEntity>
)

@Serializable
data class RepoItemsEntity(
    val full_name: String = "",
    val description: String = "",
    val forks: String = "",
    val created_at: String = "",
)
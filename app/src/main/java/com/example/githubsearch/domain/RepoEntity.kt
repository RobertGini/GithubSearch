package com.example.githubsearch.domain

class RepoEntity(
    val itemsRepo: List<RepoItemsEntity>
)


class RepoItemsEntity(
    val full_name: String = "",
    val description: String = "",
    val forks: String = "",
    val created_at: String = "",
)
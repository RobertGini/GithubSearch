package com.example.githubsearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class GithubResponse(
    @Json(name = "items")
    val items: List<RepositoryResponse>?
)

@JsonClass(generateAdapter = true)
class RepositoryResponse(
    @Json(name = "full_name")
    val full_name: String?,

    @Json(name = "description")
    val description: String?,

    @Json(name = "forks")
    val forks: String?,

    @Json(name = "created_at")
    val created_at: String?,
)


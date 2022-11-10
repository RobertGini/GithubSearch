package com.example.githubsearch.domain.iterators

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.githubsearch.data.repositories.RepositoryImpl
import com.example.githubsearch.data.repositories.RepositoryRoom
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.domain.RepoEntity
import com.example.githubsearch.domain.useCase.UseCase
import com.example.githubsearch.domain.useCase.UseCaseRoom
import javax.inject.Inject

class RepoUseCase @Inject constructor(
    val repository: RepositoryImpl,
    val repositoryRoom: RepositoryRoom
): UseCase<String, RepoEntity>, UseCaseRoom<RepoDb> {

    override suspend fun getSearchRepository(params: String): RepoEntity {
        return repository.getSearchRepository(params)
    }

    override val getDataFromDatabase: LiveData<List<RepoDb>> =
        repositoryRoom.getDataFromDatabase.asLiveData()

    override suspend fun insert(repoDb: RepoDb) {
        return repositoryRoom.insert(repoDb)
    }

    override suspend fun delete(repoDb: RepoDb) {
        return repositoryRoom.delete(repoDb)
    }
}


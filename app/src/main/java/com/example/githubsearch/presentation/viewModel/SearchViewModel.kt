package com.example.githubsearch.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.data.repositories.RepositoryImpl
import com.example.githubsearch.data.repositories.RepositoryRoom
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: RepositoryImpl,
    private val repositoryRoom: RepositoryRoom
    ) : ViewModel() {
    fun showList(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getSearchRepository(query)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insert(repoDb: RepoDb) = viewModelScope.launch {
        repositoryRoom.insert(repoDb)
    }
    fun delete(repoDb: RepoDb) = viewModelScope.launch {
        repositoryRoom.delete(repoDb)
    }
}
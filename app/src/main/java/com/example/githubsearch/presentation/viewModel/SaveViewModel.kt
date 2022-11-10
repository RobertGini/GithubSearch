package com.example.githubsearch.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.data.repositories.RepositoryRoom
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.domain.iterators.RepoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveViewModel @Inject constructor(
    private val repository: RepoUseCase
) : ViewModel() {
    val getDataFromDatabase: LiveData<List<RepoDb>> = repository.getDataFromDatabase

    fun insert(repoDb: RepoDb) = viewModelScope.launch {
        repository.insert(repoDb)
    }

    fun delete(repoDb: RepoDb) = viewModelScope.launch {
        repository.delete(repoDb)
    }
}
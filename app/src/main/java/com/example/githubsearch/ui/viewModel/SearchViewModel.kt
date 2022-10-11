package com.example.githubsearch.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.data.repositories.ApiRepository
import com.example.githubsearch.data.repositories.RoomRepository
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val apiRepository: ApiRepository,
    private val roomRepository: RoomRepository
    ) : ViewModel() {
    fun showList(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getSearchRepository(query)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insert(repoDb: RepoDb) = viewModelScope.launch {
        roomRepository.insert(repoDb)
    }
    fun delete(repoDb: RepoDb) = viewModelScope.launch {
        roomRepository.delete(repoDb)
    }
}
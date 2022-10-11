package com.example.githubsearch.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.data.repositories.RoomRepository
import com.example.githubsearch.data.room.RepoDb
import kotlinx.coroutines.launch

class SaveViewModel(
    private val roomRepository: RoomRepository
    ): ViewModel() {
    val getDataFromDatabase: LiveData<List<RepoDb>> = roomRepository.getDataFromDatabase.asLiveData()

    fun insert(repoDb: RepoDb) = viewModelScope.launch {
        roomRepository.insert(repoDb)
    }
    fun delete(repoDb: RepoDb) = viewModelScope.launch {
        roomRepository.delete(repoDb)
    }
}
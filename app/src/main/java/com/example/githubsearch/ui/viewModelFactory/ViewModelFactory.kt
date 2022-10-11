package com.example.githubsearch.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.data.repositories.ApiRepository
import com.example.githubsearch.data.repositories.RoomRepository
import com.example.githubsearch.ui.adapters.SaveAdapter
import com.example.githubsearch.ui.viewModel.DescriptionViewModel
import com.example.githubsearch.ui.viewModel.SaveViewModel
import com.example.githubsearch.ui.viewModel.SearchViewModel

class ViewModelFactory(
    private val apiRepository: ApiRepository,
    private val roomRepository: RoomRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(apiRepository, roomRepository) as T
        } else if (modelClass.isAssignableFrom(DescriptionViewModel::class.java)) {
            return DescriptionViewModel(apiRepository) as T
        } else if (modelClass.isAssignableFrom(SaveViewModel::class.java)) {
            return SaveViewModel(roomRepository) as T

        }
        throw IllegalArgumentException("Unknown class name")
    }
}
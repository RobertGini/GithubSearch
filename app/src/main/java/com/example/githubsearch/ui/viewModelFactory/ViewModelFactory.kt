package com.example.githubsearch.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.data.repositories.ApiRepository
import com.example.githubsearch.ui.viewModel.DescriptionViewModel
import com.example.githubsearch.ui.viewModel.SearchViewModel

class ViewModelFactory(
    private val apiRepository: ApiRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(apiRepository) as T

        }else if (modelClass.isAssignableFrom(DescriptionViewModel::class.java)) {
            return DescriptionViewModel(apiRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
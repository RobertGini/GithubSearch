package com.example.githubsearch.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.githubsearch.data.repositories.ApiRepository
import com.example.githubsearch.utils.Resource
import kotlinx.coroutines.Dispatchers

class SearchViewModel(
    private val apiRepository: ApiRepository
    ) : ViewModel() {
    fun showList(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getSearchRepository(query)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
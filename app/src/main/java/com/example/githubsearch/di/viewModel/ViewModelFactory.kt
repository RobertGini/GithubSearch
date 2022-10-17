package com.example.githubsearch.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

//class ViewModelFactory(
//    private val apiRepository: ApiRepository,
//    private val roomRepository: RoomRepository
//) : ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
//            return SearchViewModel(apiRepository, roomRepository) as T
//
//        } else if (modelClass.isAssignableFrom(DescriptionViewModel::class.java)) {
//            return DescriptionViewModel(apiRepository) as T
//
//        } else if (modelClass.isAssignableFrom(SaveViewModel::class.java)) {
//            return SaveViewModel(roomRepository) as T
//
//        } else {
//            throw IllegalArgumentException("Unknown class name")
//        }
//    }
//}

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
    }


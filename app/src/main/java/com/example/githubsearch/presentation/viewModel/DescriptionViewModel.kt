package com.example.githubsearch.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.githubsearch.data.repositories.RepositoryImpl
import com.example.githubsearch.presentation.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val repository: RepositoryImpl
) : ViewModel()
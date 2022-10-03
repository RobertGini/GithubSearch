package com.example.githubsearch.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.R
import com.example.githubsearch.data.api.RetrofitClient
import com.example.githubsearch.data.model.GithubResponse
import com.example.githubsearch.data.repositories.ApiRepository
import com.example.githubsearch.databinding.FragmentDescriptionBinding
import com.example.githubsearch.databinding.FragmentSaveBinding
import com.example.githubsearch.ui.viewModel.DescriptionViewModel
import com.example.githubsearch.ui.viewModel.SearchViewModel
import com.example.githubsearch.ui.viewModelFactory.ViewModelFactory
import com.example.githubsearch.utils.Status

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var descModel: DescriptionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        setupViewModel()

        val repoName = arguments?.getString("RepoName")
        if (repoName != null) {
            setupFields(repoName)
        }

        return binding.root
    }

    private fun setupFields(query: String) {
        descModel.getData(query).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val data = resource.data!!
                        setDescInfo(data)
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    fun setDescInfo(githubResponse: GithubResponse) = with(binding){
        descRepoName.text = githubResponse.items[0].full_name
        descDescription.text = githubResponse.items[0].description
        descForks.text = githubResponse.items[0].forks
        descCreatedAt.text = githubResponse.items[0].created_at
    }

    private fun setupViewModel() {
        descModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiRepository(RetrofitClient.apiService)
            )
        ).get(DescriptionViewModel::class.java)
    }
}
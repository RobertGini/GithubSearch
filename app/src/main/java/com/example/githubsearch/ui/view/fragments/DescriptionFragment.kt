package com.example.githubsearch.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.di.RetrofitClient
import com.example.githubsearch.data.repositories.ApiRepository
import com.example.githubsearch.data.room.RepoApplication
import com.example.githubsearch.databinding.FragmentDescriptionBinding
import com.example.githubsearch.domain.RepoItemsEntity
import com.example.githubsearch.ui.viewModel.DescriptionViewModel
import com.example.githubsearch.di.viewModel.ViewModelFactory
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var descModel: DescriptionViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        setupViewModel()

        arguments?.takeIf { it.containsKey("RepoName") }?. apply {

            val json = Json { ignoreUnknownKeys = true }
            val repoName = json.decodeFromString(
                RepoItemsEntity.serializer(),
                getString("RepoName")!!
            )
            setDescInfo(repoName)
        }
        return binding.root
    }

    fun setDescInfo(data: RepoItemsEntity) = with(binding){
        descRepoName.text = data.full_name
        descDescription.text = data.description
        descForks.text = data.forks
        descCreatedAt.text = data.created_at
    }

    private fun setupViewModel() {
        descModel = ViewModelProvider(
            this,
            viewModelFactory
        )[DescriptionViewModel::class.java]
//        descModel = ViewModelProvider(
//            this,
//            ViewModelFactory(
//                ApiRepository(RetrofitClient().provideApiService()),
//                (requireActivity().application as RepoApplication).repository
//            )
//        ).get(DescriptionViewModel::class.java)
    }
}
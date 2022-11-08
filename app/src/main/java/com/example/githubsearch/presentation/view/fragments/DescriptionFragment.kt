package com.example.githubsearch.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubsearch.databinding.FragmentDescriptionBinding
import com.example.githubsearch.domain.RepoItemsEntity
import kotlinx.serialization.json.Json

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)

        arguments?.takeIf { it.containsKey("RepoName") }?.apply {

            val json = Json { ignoreUnknownKeys = true }
            val repoName = json.decodeFromString(
                RepoItemsEntity.serializer(),
                getString("RepoName")!!
            )
            setDescInfo(repoName)
        }
        return binding.root
    }

    fun setDescInfo(data: RepoItemsEntity) = with(binding) {
        descRepoName.text = data.full_name
        descDescription.text = data.description
        descForks.text = data.forks
        descCreatedAt.text = data.created_at
    }


}
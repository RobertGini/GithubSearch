package com.example.githubsearch.ui.view.fragments.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.data.api.RetrofitClient
import com.example.githubsearch.data.model.GithubResponse
import com.example.githubsearch.data.repositories.ApiRepository
import com.example.githubsearch.data.room.RepoApplication
import com.example.githubsearch.databinding.FragmentSaveBinding
import com.example.githubsearch.ui.adapters.SaveAdapter
import com.example.githubsearch.ui.adapters.SearchAdapter
import com.example.githubsearch.ui.viewModel.SaveViewModel
import com.example.githubsearch.ui.viewModel.SearchViewModel
import com.example.githubsearch.ui.viewModelFactory.ViewModelFactory
import com.example.githubsearch.utils.toast

class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding
    private lateinit var saveAdapter: SaveAdapter
    private lateinit var saveRv: RecyclerView
    private lateinit var saveModel: SaveViewModel
    private var bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        setupViewModel()
        setupAdapter()
        setupList()
        return binding.root
    }

    private fun setupList() {
        saveModel.getDataFromDatabase.observe(viewLifecycleOwner) {
            saveAdapter.submitList(it)
            bundle.putString("RepoName",it[0].full_name)
            Log.d("ClickOnItem", it[0].full_name)
        }
    }

    private fun setupAdapter() {
        saveRv = binding.rcSaved
        binding.rcSaved.setHasFixedSize(true)
        binding.rcSaved.layoutManager = LinearLayoutManager(context)
        saveAdapter = SaveAdapter{position ->
            onItemClick(position)
        }
        saveRv.adapter = saveAdapter
    }

    private fun setupViewModel() {
        saveModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiRepository(RetrofitClient.apiService),
                (requireActivity().application as RepoApplication).repository
            )
        ).get(SaveViewModel::class.java)
    }

    private fun onItemClick(position: Int) {
        Log.d("Click", "Clicked on Item")
        findNavController().navigate(R.id.action_viewPagerFragment_to_descriptionFragment, bundle)
    }

    companion object {
        fun newInstance() = SaveFragment()
    }
}
package com.example.githubsearch.presentation.view.fragments.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.databinding.FragmentSearchBinding
import com.example.githubsearch.di.viewModelFactory.ViewModelFactory
import com.example.githubsearch.domain.RepoItemsEntity
import com.example.githubsearch.presentation.adapters.SearchAdapter
import com.example.githubsearch.presentation.utils.Status
import com.example.githubsearch.presentation.utils.hideKeyboard
import com.example.githubsearch.presentation.viewModel.SearchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SearchFragment : DaggerFragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var searchRv: RecyclerView
    private var bundle = Bundle()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val searchModel: SearchViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupAdapter()
        clickOnSearchView()
        return binding.root
    }

    private fun setupObservers(query: String) {
        searchModel.showList(query).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val data = resource.data!!
                        showRepoList(data.itemsRepo)
                        swipeToFavorite(data.itemsRepo)
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        searchRv = binding.rcSearch
        binding.rcSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun showRepoList(data: List<RepoItemsEntity>) {
        searchAdapter = SearchAdapter(data) { data ->
            onItemClick(data)
        }
        searchRv.adapter = searchAdapter
    }

    private fun clickOnSearchView() {
        binding.searchGithub.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    setupObservers(query)
                    hideKeyboard()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun swipeToFavorite(data: List<RepoItemsEntity>) {
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        val position = viewHolder.bindingAdapterPosition
                        val item = RepoDb(
                            data[position].full_name,
                            data[position].description,
                            data[position].forks,
                            data[position].created_at
                        )
                        searchModel.insert(item)
                        Log.d("Swipe", data[position].full_name)
                    }
                }
            }
        }
        itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(searchRv)
    }

    private fun onItemClick(data: RepoItemsEntity) {
        val jsonRepo = Json.encodeToString(RepoItemsEntity.serializer(), data)
        bundle.putString("RepoName", jsonRepo)
        Log.d("Click", jsonRepo)
        findNavController().navigate(R.id.action_viewPagerFragment_to_descriptionFragment, bundle)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
package com.example.githubsearch.presentation.view.fragments.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.data.room.RepoDb
import com.example.githubsearch.databinding.FragmentSaveBinding
import com.example.githubsearch.presentation.adapters.SaveAdapter
import com.example.githubsearch.presentation.viewModel.SaveViewModel
import com.example.githubsearch.di.viewModelFactory.ViewModelFactory
import com.example.githubsearch.presentation.utils.hideKeyboard
import dagger.android.support.DaggerFragment
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SaveFragment : DaggerFragment(R.layout.fragment_save) {
    private lateinit var binding: FragmentSaveBinding
    private lateinit var saveAdapter: SaveAdapter
    private lateinit var saveRv: RecyclerView
    private lateinit var itemTouchHelper: ItemTouchHelper
    private var bundle = Bundle()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val saveModel: SaveViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        setupAdapter()
        setupList()
        swipeToDelete()
        clickOnSearchView()
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

    private fun clickOnSearchView() {
        binding.searchSaved.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    bundle.putString("RepoName", query)
                    hideKeyboard()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun swipeToDelete(){
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction) {
                    ItemTouchHelper.LEFT -> {
                        val position = viewHolder.bindingAdapterPosition
                        val repoDb = saveAdapter.currentList.get(position)
                        saveModel.delete(repoDb)
                        Log.d("Swipe", "Swiped left")
                    }
                }
            }
        }
        itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(saveRv)
    }

    private fun onItemClick(data: RepoDb) {
        val jsonRepo = Json.encodeToString(RepoDb.serializer(), data)
        bundle.putString("RepoName", jsonRepo)
        Log.d("Click", "")
        findNavController().navigate(R.id.action_viewPagerFragment_to_descriptionFragment, bundle)
    }

    companion object {
        fun newInstance() = SaveFragment()
    }
}
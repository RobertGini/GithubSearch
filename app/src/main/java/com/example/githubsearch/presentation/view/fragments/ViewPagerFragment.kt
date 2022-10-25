package com.example.githubsearch.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.githubsearch.R
import com.example.githubsearch.databinding.FragmentViewPagerBinding
import com.example.githubsearch.presentation.adapters.ViewPagerAdapter
import com.example.githubsearch.presentation.view.fragments.screens.SaveFragment
import com.example.githubsearch.presentation.view.fragments.screens.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        setupViewPager2()

        auth = Firebase.auth

        binding.floatingButton.setOnClickListener{
            auth.signOut()
            findNavController().navigate(R.id.action_viewPagerFragment_to_login_fragment)
        }
        return binding.root
    }

    private fun setupViewPager2() {
        val adapter = ViewPagerAdapter(
            childFragmentManager,
            fragmentList,
            lifecycle,
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.mainTab, binding.viewPager) { tab, pos ->
            tab.text = fragListTitles[pos]
        }.attach()
    }

    companion object {
        private val fragmentList = listOf(
            SearchFragment.newInstance(),
            SaveFragment.newInstance(),
        )
        private val fragListTitles = listOf(
            "Search",
            "Saved",
        )
    }
}
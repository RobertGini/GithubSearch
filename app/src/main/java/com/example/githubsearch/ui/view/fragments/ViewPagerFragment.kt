package com.example.githubsearch.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubsearch.databinding.FragmentViewPagerBinding
import com.example.githubsearch.ui.adapters.ViewPagerAdapter
import com.example.githubsearch.ui.view.fragments.screens.SaveFragment
import com.example.githubsearch.ui.view.fragments.screens.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        setupViewPager2()
        return binding.root
    }


    private fun setupViewPager2() {
        val adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            fragmentList,
            lifecycle,
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.mainTab, binding.viewPager) { tab, pos ->
            tab.text = fragListTitles[pos]
        }.attach()
    }

    companion object {
        private val fragmentList = listOf<Fragment>(
            SearchFragment.newInstance(),
            SaveFragment.newInstance(),
        )
        private val fragListTitles = listOf(
            "Search",
            "Saved",
        )
    }
}
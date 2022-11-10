package com.example.githubsearch.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubsearch.R
import com.example.githubsearch.databinding.FragmentViewPagerBinding
import com.example.githubsearch.presentation.adapters.ViewPagerAdapter
import com.example.githubsearch.presentation.view.fragments.screens.SaveFragment
import com.example.githubsearch.presentation.view.fragments.screens.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.android.support.DaggerFragment

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private val auth by lazy { Firebase.auth }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAuth()

        binding.floatingButton.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_viewPagerFragment_to_login_fragment)
        }
    }

    private fun setupViewPager2(list: List<DaggerFragment>, titles: List<String>) {
            val adapter = ViewPagerAdapter(
                childFragmentManager,
                list,
                lifecycle,
            )
            binding.viewPager.adapter = adapter
            TabLayoutMediator(binding.mainTab, binding.viewPager) { tab, pos ->
                tab.text = titles[pos]
            }.attach()
    }

    private fun checkAuth() {
        if (auth.currentUser != null){
            setupViewPager2(fragmentList, fragListTitles)
        } else {
            setupViewPager2(fragmentList.subList(0,1), fragListTitles.subList(0,1))
        }
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
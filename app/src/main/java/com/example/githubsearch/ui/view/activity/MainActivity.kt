package com.example.githubsearch.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.githubsearch.R
import com.example.githubsearch.databinding.ActivityMainBinding
import com.example.githubsearch.ui.adapters.ViewPagerAdapter
import com.example.githubsearch.ui.view.fragments.LoginFragment
import com.example.githubsearch.ui.view.fragments.SaveFragment
import com.example.githubsearch.ui.view.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fragList = listOf(
        SearchFragment.newInstance(),
        SaveFragment.newInstance(),
    )

    private val fragListTitles = listOf(
        "Search",
        "Saved",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this, fragList)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.mainTab, binding.viewPager){
            tab, pos -> tab.text = fragListTitles[pos]
        }.attach()
    }

}
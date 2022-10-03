package com.example.githubsearch.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubsearch.databinding.ActivityMainBinding
import com.example.githubsearch.ui.view.fragments.screens.SaveFragment
import com.example.githubsearch.ui.view.fragments.screens.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
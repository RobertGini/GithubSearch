package com.example.githubsearch.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubsearch.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment_MembersInjector

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
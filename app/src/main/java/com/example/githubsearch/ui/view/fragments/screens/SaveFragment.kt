package com.example.githubsearch.ui.view.fragments.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubsearch.databinding.FragmentSaveBinding

class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root

    }

    companion object {
        fun newInstance() = SaveFragment()
    }
}
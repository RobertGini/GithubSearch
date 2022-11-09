package com.example.githubsearch.di.modules

import com.example.githubsearch.di.scopes.FragmentScoped
import com.example.githubsearch.presentation.view.fragments.DescriptionFragment
import com.example.githubsearch.presentation.view.fragments.LoginFragment
import com.example.githubsearch.presentation.view.fragments.ViewPagerFragment
import com.example.githubsearch.presentation.view.fragments.screens.SaveFragment
import com.example.githubsearch.presentation.view.fragments.screens.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun saveFragment(): SaveFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment
}
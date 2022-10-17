package com.example.githubsearch.di

import com.example.githubsearch.di.scopes.FragmentScoped
import com.example.githubsearch.ui.view.fragments.DescriptionFragment
import com.example.githubsearch.ui.view.fragments.LoginFragment
import com.example.githubsearch.ui.view.fragments.screens.SaveFragment
import com.example.githubsearch.ui.view.fragments.screens.SearchFragment
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
    abstract fun descriptionFragment(): DescriptionFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment
}
package com.example.githubsearch.di

import com.example.githubsearch.di.scopes.ActivityScoped
import com.example.githubsearch.presentation.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            FragmentModule::class
        ]
    )
    abstract fun providesMainActivity(): MainActivity
}
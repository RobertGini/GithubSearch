package com.example.githubsearch.di

import android.app.Application
import android.content.Context
import com.example.githubsearch.di.viewModel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun context(application: Application): Context

}
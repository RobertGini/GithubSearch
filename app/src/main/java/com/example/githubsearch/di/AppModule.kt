package com.example.githubsearch.di

import android.app.Application
import android.content.Context
import com.example.githubsearch.di.viewModelFactory.ViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}
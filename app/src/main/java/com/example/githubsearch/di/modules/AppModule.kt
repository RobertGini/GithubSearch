package com.example.githubsearch.di.modules

import com.example.githubsearch.di.viewModelFactory.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        DataModule::class
    ]
)
abstract class AppModule
package com.example.githubsearch.di

import com.example.githubsearch.data.api.RetrofitClient
import dagger.Module

@Module(includes = [RetrofitClient::class])
abstract class DataModule {
}
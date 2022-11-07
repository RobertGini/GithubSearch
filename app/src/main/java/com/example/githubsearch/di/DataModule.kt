package com.example.githubsearch.di

import com.example.githubsearch.data.repositories.RepositoryImpl
import com.example.githubsearch.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitClient::class]
        //RoomModule::class]
)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideRepository(repositoryImpl: RepositoryImpl): Repository
}
package com.example.githubsearch.di.modules

import com.example.githubsearch.data.mapper.ResponseDataMapper
import com.example.githubsearch.data.repositories.RepositoryImpl
import com.example.githubsearch.data.repositories.RepositoryRoom
import com.example.githubsearch.domain.iterators.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        RoomModule::class,
        ResponseDataMapper::class
    ]
)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideRepository(repositoryImpl: RepositoryImpl): Repository

    @Singleton
    @Binds
    abstract fun provideRepositoryRoom(repositoryRoom: RepositoryRoom): Repository
}
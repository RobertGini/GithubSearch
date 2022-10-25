package com.example.githubsearch.di

import android.content.Context
import com.example.githubsearch.data.room.RepoDao
import com.example.githubsearch.data.room.RepoDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class RoomModule {

    val applicationScope = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun getDao(repoDatabase: RepoDatabase) : RepoDao {
        return repoDatabase.repoDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance() : RepoDatabase {
        return RepoDatabase.getDatabase(provideAppContext(), applicationScope)
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return provideAppContext().applicationContext
    }
}
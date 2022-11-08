package com.example.githubsearch.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.githubsearch.data.room.RepoDao
import com.example.githubsearch.data.room.RepoDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class RoomModule() {
    @Singleton
    @Provides
    fun getDao(repoDatabase: RepoDatabase): RepoDao {
        return repoDatabase.repoDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(app: Application): RepoDatabase {
        return Room.databaseBuilder(app, RepoDatabase::class.java, "repo_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}


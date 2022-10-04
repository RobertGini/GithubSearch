package com.example.githubsearch.data.room

import android.app.Application
import com.example.githubsearch.data.repositories.RoomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RepoApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { RepoDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { RoomRepository(database.repoDao()) }
}
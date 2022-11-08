package com.example.githubsearch.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepoDb::class], version = 1, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}

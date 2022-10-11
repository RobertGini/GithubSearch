package com.example.githubsearch.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [RepoDb::class], version = 1, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao

    private class RepoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.repoDao())
                }

            }
        }

        suspend fun populateDatabase(repoDao: RepoDao) {
            repoDao.deleteAllItems()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RepoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java,
                    "repo_database"
                )
                    .addCallback(RepoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
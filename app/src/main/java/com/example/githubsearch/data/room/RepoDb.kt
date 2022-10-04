package com.example.githubsearch.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "repo")
class RepoDb(
    @ColumnInfo(name = "full_name")
    val full_name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "forks")
    val forks: String,

    @ColumnInfo(name = "created_at")
    val created_at: String,

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
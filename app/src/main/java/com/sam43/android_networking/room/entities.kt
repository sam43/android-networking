package com.sam43.android_networking.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "info") val info: String?,
    @ColumnInfo(name = "name") val name: String?
)
package com.sam43.android_networking.room

import androidx.room.*

interface Daos {
    @Dao
    interface UserDao {
        @Query("SELECT * FROM movie_table")
        fun getAll(): List<Movie>

        @Query("SELECT * FROM movie_table WHERE name LIKE :name")
        fun findByName(name: String): Movie

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg movie: Movie)

        @Delete
        fun delete(movie: Movie)

        @Update
        fun updateUser(vararg movie: Movie)
    }
}
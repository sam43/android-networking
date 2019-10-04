package com.sam43.android_networking.room

import androidx.room.*

interface Daos {
    @Dao
    interface UserDao {
        @Query("SELECT * FROM user_table")
        fun getAll(): List<User>

        @Query("SELECT * FROM user_table WHERE name LIKE :name")
        fun findByName(name: String): User

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(vararg user: User)

        @Delete
        fun delete(user: User)

        @Update
        fun updateUser(vararg user: User)
    }
}
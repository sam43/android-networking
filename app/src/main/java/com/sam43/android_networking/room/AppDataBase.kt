/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/4/19 11:52 PM
 */
package com.sam43.android_networking.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Movie::class], version = 1, exportSchema = false)
/**
 * @Caution
 * Think before naming of your Entity method name and other components
 * of Room before executing once at most. ****IMPORTANT
 * TestCase:: changing 'version = 1' to 'version = 2';
 * Solution:: Clear the AppData from 'Tools' and rerun...
 * Cause I updated/refactored the table name (user_table to movie_table) and Entity (User to Movie) as well
 * */
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): Daos.UserDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "app-test.db"
        )
            .build()
    }
}
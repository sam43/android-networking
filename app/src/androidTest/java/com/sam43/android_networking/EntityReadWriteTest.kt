package com.sam43.android_networking

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.sam43.android_networking.room.AppDataBase
import com.sam43.android_networking.room.Daos
import com.sam43.android_networking.room.Movie
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EntityReadWriteTest {
    private lateinit var userDao: Daos.UserDao
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getContext()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val movie = Movie(
            id = 100200300,
            name = "test name",
            info = "test information",
            image = "test_image_url"
        )
        userDao.insert(movie)
        val todoItem = userDao.findByName(movie.name.toString())
        assertThat(todoItem, equalTo(movie))
    }
}
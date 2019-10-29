/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/29/19 3:03 AM
 */
package com.sam43.android_networking.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sam43.android_networking.room.Movie
import com.sam43.android_networking.utils.ViewModelFactory
import com.sam43.android_networking.utils.getAllFavorites
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModelFactory() {


    val addedMovieList = MutableLiveData<List<Movie?>>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchAddedMovies(context: Context) {
        scope.launch {
            val popularMovies = context.getAllFavorites()
            addedMovieList.postValue(popularMovies)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/29/19 12:27 AM
 */
package com.sam43.android_networking.ui.home

import androidx.lifecycle.MutableLiveData
import com.sam43.android_networking.models.MovieItem
import com.sam43.android_networking.services.ApiFactory
import com.sam43.android_networking.utils.ViewModelFactory
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModelFactory() {
    private val repository : MovieListRepo = MovieListRepo(ApiFactory.tmdbApi)
    val popularMoviesLiveData = MutableLiveData<MutableList<MovieItem?>>()

    fun fetchMovies(){
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            popularMoviesLiveData.postValue(popularMovies)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
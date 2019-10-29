/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/28/19 5:14 PM
 */
package com.sam43.android_networking.ui.home.details

import androidx.lifecycle.MutableLiveData
import com.sam43.android_networking.models.MovieDetails
import com.sam43.android_networking.services.ApiFactory
import com.sam43.android_networking.utils.ViewModelFactory
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModelFactory() {

    private val repository: MovieDetailRepo = MovieDetailRepo(ApiFactory.tmdbApi)
    val movieDetails = MutableLiveData<MovieDetails?>()

    fun fetchMovieDetails(movieID: Int) {
        scope.launch {
            val popularMovies = repository.getPopularMovieDetails(movieID)
            movieDetails.postValue(popularMovies)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}

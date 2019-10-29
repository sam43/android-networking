/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/28/19 10:41 AM
 */
package com.sam43.android_networking.ui.home

import com.sam43.android_networking.models.MovieItem
import com.sam43.android_networking.services.BaseRepository
import com.sam43.android_networking.services.TmdbApi

class MovieListRepo (private val api : TmdbApi) : BaseRepository() {

    suspend fun getPopularMovies() : MutableList<MovieItem?>?{
        val movieResponse = safeApiCall(
            call = { api.getPopularMoviesAsync().await() },
            errorMessage = "Error Fetching Popular Movies"
        )
        return movieResponse?.respResults?.toMutableList()
    }
}
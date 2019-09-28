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
/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/28/19 5:14 PM
 */
package com.sam43.android_networking.ui.home.details

import com.sam43.android_networking.models.MovieDetails
import com.sam43.android_networking.services.BaseRepository
import com.sam43.android_networking.services.TmdbApi

class MovieDetailRepo(private val api: TmdbApi) : BaseRepository() {

    suspend fun getPopularMovieDetails(movieID: Int): MovieDetails? {
        return safeApiCall(
            call = { api.getMovieByIdAsync(movieID).await() },
            errorMessage = "Error Fetching Popular Movies"
        )
    }
}
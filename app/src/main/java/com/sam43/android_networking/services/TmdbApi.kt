package com.sam43.android_networking.services

import com.sam43.android_networking.models.MovieDetails
import com.sam43.android_networking.models.PopularMovies
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {
    @GET("movie/popular")
    fun getPopularMoviesAsync(): Deferred<Response<PopularMovies>>
    @GET("movie/{id}")
    fun getMovieByIdAsync(@Path("id") id: Int): Deferred<Response<MovieDetails>>
}

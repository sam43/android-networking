package com.sam43.android_networking.ui.home.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sam43.android_networking.models.MovieDetails
import com.sam43.android_networking.room.Movie
import com.sam43.android_networking.services.ApiFactory
import com.sam43.android_networking.utils.ViewModelFactory
import com.sam43.android_networking.utils.isAlreadyFavorite
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModelFactory() {

    private val repository: MovieDetailRepo = MovieDetailRepo(ApiFactory.tmdbApi)
    val movieDetails = MutableLiveData<MovieDetails?>()
    val isFavorite = MutableLiveData<Boolean>()

    fun fetchMovieDetails(movieID: Int) {
        scope.launch {
            val popularMovies = repository.getPopularMovieDetails(movieID)
            movieDetails.postValue(popularMovies)
        }
    }

    fun checkIfAlreadyInFavorites(context: Context, movieID: Int) {
        scope.launch {
            val data = context.isAlreadyFavorite(movieID)

            isFavorite.postValue(data != null)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}

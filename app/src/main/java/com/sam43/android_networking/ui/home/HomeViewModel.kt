package com.sam43.android_networking.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sam43.android_networking.models.MovieItem
import com.sam43.android_networking.services.ApiFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

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
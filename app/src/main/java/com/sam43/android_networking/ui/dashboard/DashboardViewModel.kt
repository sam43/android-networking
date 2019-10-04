package com.sam43.android_networking.ui.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sam43.android_networking.room.User
import com.sam43.android_networking.utils.ViewModelFactory
import com.sam43.android_networking.utils.getAllUser
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModelFactory() {


    val addedMovieList = MutableLiveData<List<User?>>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchAddedMovies(context: Context) {
        scope.launch {
            val popularMovies = context.getAllUser()
            addedMovieList.postValue(popularMovies)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
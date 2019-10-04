package com.sam43.android_networking.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sam43.android_networking.room.AppDataBase
import com.sam43.android_networking.room.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val baseUrl = "https://image.tmdb.org/t/p/w500_and_h282_face/"

fun Context.loadImage(url: String, imageView: ImageView, placeHolder: Int, errorHolder: Int) {
    Glide.with(this)
        .load(baseUrl.plus(url))
        .transition(DrawableTransitionOptions.withCrossFade(200))
        .apply(
            RequestOptions.placeholderOf(placeHolder)
                .error(errorHolder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
        )
        .into(imageView)

}

fun Context.loadCircularImage(url: String?, holder: ImageView, placeHolder: Int, errorHolder: Int) {
    Glide.with(this)
        .load(baseUrl.plus(url))
        .transition(DrawableTransitionOptions.withCrossFade(200))
        .apply(
            RequestOptions.placeholderOf(placeHolder)
                .circleCrop()
                .error(errorHolder)
                .dontAnimate().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
        )
        .into(holder)

}

fun Context.insert(movie: Movie) {
    val db = AppDataBase.invoke(this)
    GlobalScope.launch {
        db.userDao().insert(movie)
        Log.d("DB_insert", "inserted")
    }
}

fun Context.delete(movie: Movie) {
    val db = AppDataBase.invoke(this)
    GlobalScope.launch {
        db.userDao().delete(movie)
        Log.d("DB_delete", "deleted")
    }
}

fun Context.updateUser(movie: Movie) {
    val db = AppDataBase.invoke(this)
    GlobalScope.launch {
        db.userDao().updateUser(movie)
    }
}

fun Context.filterUserbyName(userName: String) {
    val db = AppDataBase.invoke(this)
    GlobalScope.launch {
        db.userDao().findByName(userName)
    }
}

fun Context.getAllUser(): List<Movie?> {
    val db = AppDataBase.invoke(this)
    val movieList: ArrayList<Movie?> = ArrayList()
    //var data: List<Movie?> = listOf()
    GlobalScope.launch {
        val data = db.userDao().getAll()
        movieList.addAll(data)
        data.forEach {
            Log.d("DB_movie", "val: ${it.name} and id: ${it.id}")
        }
    }
    return movieList
}

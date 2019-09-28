package com.sam43.android_networking.utils

import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

private const val baseUrl = "https://image.tmdb.org/t/p/w500_and_h282_face/"

fun FragmentActivity.loadFragment(container: Int, fragment: Fragment) {
    this.supportFragmentManager
        .beginTransaction()
        .replace(container, fragment)
        .addToBackStack(null)
        .commit()
}


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
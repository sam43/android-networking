package com.sam43.android_networking.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sam43.android_networking.utils.AppConsts
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    //ApiFactory to create TMDB Api

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", AppConsts.tmdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val loggingInterceptor =  HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //OkhttpClient for building http request url
    private val tmdbClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()


    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val tmdbApi: TmdbApi = retrofit().create(TmdbApi::class.java)
}
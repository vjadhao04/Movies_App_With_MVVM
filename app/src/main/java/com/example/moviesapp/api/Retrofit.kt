package com.example.moviesapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val POSTER_BASE_URL: String = "https://image.tmdb.org/t/p/w342"

object Retrofit {

    private const val  BASE_URL="https://api.themoviedb.org/3/"


    val client = OkHttpClient.Builder()

        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()
    fun getInstance(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
           .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }


}

package com.example.moviesapp.api

import com.example.moviesapp.models.MoviesList
import com.example.moviesapp.models.SingleMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") api_key:String,@Query("page") page:Int  ):Response<MoviesList>

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int, @Query("api_key") api_key:String  ):Response<SingleMovie>

}
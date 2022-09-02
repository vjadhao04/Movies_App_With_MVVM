package com.example.moviesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.moviesapp.api.RetrofitService

import com.example.moviesapp.models.SingleMovie
import com.example.moviesapp.paging.MoviesPagingSource

class MoviesRepository (private val retrofitService: RetrofitService) {

    private val mutableLiveData= MutableLiveData<SingleMovie>()
    val movies: LiveData<SingleMovie>
    get() = mutableLiveData

    //to get a single movie by providing ID
    suspend fun getMovie( id :Int){
        val result=retrofitService.getMovie(id,"323351aac3b04130cfe5c7929b04eec4")

        if (result.body() != null){
            mutableLiveData.postValue(result.body())
        }

    }


    //paging for get multiple movies
    fun getMovie()=Pager(
        config = PagingConfig(20,100),
        pagingSourceFactory = {MoviesPagingSource(retrofitService)}
    ).liveData


}
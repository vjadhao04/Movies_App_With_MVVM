package com.example.moviesapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.api.RetrofitService
import com.example.moviesapp.models.Result

class MoviesPagingSource(private val retrofitService: RetrofitService):PagingSource<Int,Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {

            val position = params.key ?: 1



            val response = retrofitService.getMovies("323351aac3b04130cfe5c7929b04eec4", position)
            if(response.isSuccessful)
            println("-----------after retrofit on source---------------")
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if(position==1) null else position-1,
                nextKey = if(response.body()!!.total_pages==position) null else position+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
//    fun getInt():Int{
//
//    }
}
package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.api.Retrofit
import com.example.moviesapp.api.RetrofitService
import com.example.moviesapp.paging.LoaderAdapter
import com.example.moviesapp.paging.MoviesPagingAdapter
import com.example.moviesapp.repository.MoviesRepository
import com.example.moviesapp.viewmodels.MainViewModel
import com.example.moviesapp.viewmodels.ViewModelFactory
import javax.inject.Inject

interface MainInterface{
    fun onMovieItemClick( id :Int)
}


class MainActivity : AppCompatActivity(),MainInterface {


    private lateinit var moviesRepository: MoviesRepository
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitService= Retrofit.getInstance().create(RetrofitService::class.java )
        moviesRepository=MoviesRepository(retrofitService)

        mainViewModel=ViewModelProvider(this, ViewModelFactory(moviesRepository)).get(MainViewModel::class.java)

        recyclerView=findViewById(R.id.movieRV)
        adapter= MoviesPagingAdapter(this,this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter.withLoadStateFooter(
            LoaderAdapter(this)
        )

        val movies=mainViewModel.movies

        movies.observe(this, Observer {
            adapter.submitData(lifecycle,it)
        })


    }

    override fun onMovieItemClick(id: Int) {
        val intent=Intent(this,MovieDetails::class.java).putExtra("id",id)
        startActivity(intent)

    }
}
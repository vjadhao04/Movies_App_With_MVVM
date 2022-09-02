package com.example.moviesapp

import android.content.Intent
import android.content.ServiceConnection
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviesapp.api.POSTER_BASE_URL
import com.example.moviesapp.api.Retrofit
import com.example.moviesapp.api.RetrofitService
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding
import com.example.moviesapp.repository.MoviesRepository
import com.example.moviesapp.viewmodels.MovieDetailsViewModel
import com.example.moviesapp.viewmodels.MovieDetailsViewModelFactory
import java.util.concurrent.Executor

class MovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_movie_details)


        retrofitService= Retrofit.getInstance().create(RetrofitService::class.java )
        moviesRepository= MoviesRepository(retrofitService)
        movieDetailsViewModel=ViewModelProvider(this,MovieDetailsViewModelFactory(moviesRepository)).get(MovieDetailsViewModel::class.java)
        movieDetailsViewModel.getMovie(intent.getIntExtra("id",0))
        val movie=movieDetailsViewModel.movie
        var title=""


        movie.observe(this, Observer {
            binding.it=it
            title=it.title
            binding.movieBudget.text=it.budget.toString()
            //binding.movieTitle.text=it.title
           // binding.movieTagline.text=it.tagline
           // binding.movieOverview.text=it.overview
            binding.movieRuntime.text=it.runtime.toString()+" Minutes"
            binding.movieRating.text=it.popularity.toString()
            binding.movieRevenue.text=it.revenue.toString()
            //binding.movieReleaseDate.text=it.release_date

            Glide
                .with(this)
                .load(POSTER_BASE_URL + it.poster_path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.ivMoviePoster)
        })

        binding.movieTitle.setOnClickListener {
            val intent=Intent(this,WebViewActivity::class.java).putExtra("title",title)
            startActivity(intent)
        }




        //Toast.makeText(this,intent.getIntExtra("id",0).toString(),Toast.LENGTH_LONG).show()


    }
}
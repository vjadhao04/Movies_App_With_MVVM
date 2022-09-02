package com.example.moviesapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(val moviesRepository: MoviesRepository):ViewModel() {

    fun getMovie(id:Int){
        viewModelScope.launch {
            moviesRepository.getMovie(id)
        }
    }
    val movie=moviesRepository.movies

}
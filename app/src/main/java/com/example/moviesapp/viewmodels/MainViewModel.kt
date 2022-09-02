package com.example.moviesapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviesapp.models.MoviesList
import com.example.moviesapp.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val moviesRepository: MoviesRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.getMovie(1)
        }
    }
    val movies=moviesRepository.getMovie().cachedIn(viewModelScope)



}
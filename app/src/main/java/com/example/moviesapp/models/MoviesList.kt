package com.example.moviesapp.models

data class MoviesList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
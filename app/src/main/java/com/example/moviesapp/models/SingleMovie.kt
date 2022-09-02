package com.example.moviesapp.models

data class SingleMovie(
    val adult: Boolean,
    val backdrop_path: String,

    val budget: Int,
    val homepage: String,
    val id: Int,

    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,

    val release_date: String,
    val revenue: Int,
    val runtime: Double,

    val status: String,
    val tagline: String,
    val title: String,

    val vote_average: Double,
    val vote_count: Int
)
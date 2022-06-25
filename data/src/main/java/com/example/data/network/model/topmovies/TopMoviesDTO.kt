package com.example.data.network.model.topmovies

data class TopMoviesDTO(
    val errorMessage: String,
    val items: List<TopMoviesDTOItem>
)
package com.example.data.network.model

data class TopMoviesDTO(
    val errorMessage: String,
    val items: List<TopMoviesDTOItem>
)
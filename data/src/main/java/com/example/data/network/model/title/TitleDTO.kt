package com.example.data.network.model.title

data class TitleDTO(
    val companies: String,
    val directors: String,
    val errorMessage: String?,
    val fullTitle: String,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val image: String,
    val languages: String,
    val plot: String,
    val releaseDate: String,
    val runtimeMins: String,
    val stars: String,
    val writers: String,
    val year: String
)
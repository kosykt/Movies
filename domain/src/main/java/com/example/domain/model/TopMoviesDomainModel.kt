package com.example.domain.model

data class TopMoviesDomainModel(
    val errorMessage: String,
    val items: List<TopMoviesDomainItem>
)
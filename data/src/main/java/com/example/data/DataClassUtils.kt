package com.example.data

import com.example.data.database.model.TopMoviesEntity
import com.example.data.network.model.TopMoviesDTOItem
import com.example.domain.model.TopMoviesDomainModel


fun List<TopMoviesDTOItem>.toListTopMoviesDomainModel() = this.map {
    TopMoviesDomainModel(
        crew = it.crew,
        fullTitle = it.fullTitle,
        id = it.id,
        imDbRating = it.imDbRating,
        imDbRatingCount = it.imDbRatingCount,
        image = it.image,
        rank = it.rank,
        title = it.title,
        year = it.year,
    )
}

fun List<TopMoviesDTOItem>.toListTopMoviesEntity() = this.map {
    TopMoviesEntity(
        crew = it.crew,
        fullTitle = it.fullTitle,
        id = it.id,
        imDbRating = it.imDbRating,
        imDbRatingCount = it.imDbRatingCount,
        image = it.image,
        rank = it.rank,
        title = it.title,
        year = it.year,
    )
}
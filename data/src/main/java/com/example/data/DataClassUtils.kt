package com.example.data

import com.example.data.database.model.TopMoviesEntity
import com.example.data.network.model.TopMoviesDTO
import com.example.data.network.model.TopMoviesDTOItem
import com.example.domain.model.TopMoviesDomainItem
import com.example.domain.model.TopMoviesDomainModel

fun TopMoviesDTO.toTopMoviesDomainModel() = TopMoviesDomainModel(
    errorMessage = this.errorMessage,
    items = this.items.toListTopMoviesDomainItem()
)

fun List<TopMoviesDTOItem>.toListTopMoviesDomainItem() = this.map {
    TopMoviesDomainItem(
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

fun getErrorTop250Movies() = TopMoviesDomainModel(
    errorMessage = "ERROR",
    items = emptyList()
)
package com.example.data

import com.example.data.database.model.TopMoviesEntity
import com.example.data.network.model.title.TitleDTO
import com.example.data.network.model.topmovies.TopMoviesDTOItem
import com.example.domain.model.TitleDomainModel
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

fun TitleDTO.toTitleDomainModel() = TitleDomainModel(
    companies = this.companies,
    directors = this.directors,
    errorMessage = this.errorMessage,
    fullTitle = this.fullTitle,
    genres = this.genres,
    id = this.id,
    imDbRating = this.imDbRating,
    image = this.image,
    languages = this.languages,
    plot = this.plot,
    releaseDate = this.releaseDate,
    runtimeMins = this.runtimeMins,
    stars = this.stars,
    writers = this.writers,
    year = this.year,
)
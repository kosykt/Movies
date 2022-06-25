package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TitleEntity(
    @PrimaryKey
    val id: String,
    val companies: String,
    val directors: String,
    val errorMessage: String?,
    val fullTitle: String,
    val genres: String,
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
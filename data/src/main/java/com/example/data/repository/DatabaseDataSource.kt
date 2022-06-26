package com.example.data.repository

import com.example.data.database.model.TitleEntity
import com.example.data.database.model.TopMoviesEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseDataSource {

    suspend fun insertTopMovies(topMoviesEntity: List<TopMoviesEntity>)
    suspend fun getAllTopMovies(): List<TopMoviesEntity>

    suspend fun insertTitle(titleEntity: TitleEntity)
    suspend fun getTitleById(titleId: String): TitleEntity
}
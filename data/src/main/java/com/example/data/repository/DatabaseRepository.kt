package com.example.data.repository

import com.example.data.database.model.TopMoviesEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun insert(topMoviesEntity: List<TopMoviesEntity>)
    fun getAll(): Flow<List<TopMoviesEntity>>
}
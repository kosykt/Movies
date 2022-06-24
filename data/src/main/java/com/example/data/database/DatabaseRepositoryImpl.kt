package com.example.data.database

import com.example.data.database.model.TopMoviesEntity
import com.example.data.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(
    private val database: AppDatabase
) : DatabaseRepository {
    override suspend fun insert(topMoviesEntity: List<TopMoviesEntity>) {
        database.topMoviesDao().insert(topMoviesEntity)
    }

    override fun getAll(): Flow<List<TopMoviesEntity>> {
        return database.topMoviesDao().getAll()
    }
}
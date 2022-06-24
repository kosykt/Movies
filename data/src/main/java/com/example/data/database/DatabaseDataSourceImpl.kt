package com.example.data.database

import com.example.data.database.model.TopMoviesEntity
import com.example.data.repository.DatabaseDataSource
import kotlinx.coroutines.flow.Flow

class DatabaseDataSourceImpl(
    private val database: AppDatabase
) : DatabaseDataSource {
    override suspend fun insert(topMoviesEntity: List<TopMoviesEntity>) {
        database.topMoviesDao().insert(topMoviesEntity)
    }

    override fun getAll(): Flow<List<TopMoviesEntity>> {
        return database.topMoviesDao().getAll()
    }
}
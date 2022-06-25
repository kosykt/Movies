package com.example.data.database

import com.example.data.database.model.TitleEntity
import com.example.data.database.model.TopMoviesEntity
import com.example.data.repository.DatabaseDataSource
import kotlinx.coroutines.flow.Flow

class DatabaseDataSourceImpl(
    private val database: AppDatabase
) : DatabaseDataSource {
    override suspend fun insertTopMovies(topMoviesEntity: List<TopMoviesEntity>) {
        database.topMoviesDao().insert(topMoviesEntity)
    }

    override fun getAllTopMovies(): Flow<List<TopMoviesEntity>> {
        return database.topMoviesDao().getAll()
    }

    override suspend fun insertTitle(titleEntity: TitleEntity) {
        database.titleDao().insert(titleEntity)
    }

    override suspend fun getTitleById(titleId: String): TitleEntity {
        return database.titleDao().getById(titleId)
    }
}
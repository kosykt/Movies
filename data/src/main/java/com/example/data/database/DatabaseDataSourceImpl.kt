package com.example.data.database

import com.example.data.database.model.TitleEntity
import com.example.data.database.model.TopMoviesEntity
import com.example.data.repository.DatabaseDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class DatabaseDataSourceImpl(
    private val database: AppDatabase
) : DatabaseDataSource {
    override suspend fun insertTopMovies(topMoviesEntity: List<TopMoviesEntity>) {
        withContext(Dispatchers.IO) { database.topMoviesDao().insert(topMoviesEntity) }
    }

    override suspend fun getAllTopMovies(): List<TopMoviesEntity> {
        return database.topMoviesDao().getAll()
    }

    override suspend fun insertTitle(titleEntity: TitleEntity) {
        withContext(Dispatchers.IO) { database.titleDao().insert(titleEntity) }
    }

    override suspend fun getTitleById(titleId: String): TitleEntity {
        return withContext(Dispatchers.IO) { database.titleDao().getById(titleId) }
    }
}
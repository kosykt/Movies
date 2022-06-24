package com.example.data.repository

import com.example.data.toListTopMoviesDomainModel
import com.example.data.toListTopMoviesEntity
import com.example.domain.DataSourceRepository
import com.example.domain.UseCaseResponse

class DataSourceRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource,
) : DataSourceRepository {
    override suspend fun getTop250Movies(): UseCaseResponse {
        val response = networkDataSource.getTop250Movies()
        return when {
            response.isSuccessful && response.body() != null -> {
                databaseDataSource.insert(response.body()!!.items.toListTopMoviesEntity())
                UseCaseResponse.Success(response.body()!!.items.toListTopMoviesDomainModel())
            }
            response.isSuccessful && response.body() == null -> {
                UseCaseResponse.Error("response is empty")
            }
            else -> {
                UseCaseResponse.Error("unknown error")
            }
        }
    }
}
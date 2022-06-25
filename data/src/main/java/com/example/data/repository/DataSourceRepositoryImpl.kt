package com.example.data.repository

import com.example.data.toListTopMoviesDomainModel
import com.example.data.toListTopMoviesEntity
import com.example.data.toTitleDomainModel
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

    override suspend fun getDetails(titleId: String): UseCaseResponse {
        val response = networkDataSource.getDetails(titleId)
        return when {
            response.isSuccessful && response.body() != null -> {
                UseCaseResponse.Success(response.body()!!.toTitleDomainModel())
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
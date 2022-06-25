package com.example.data.repository

import com.example.data.toListTopMoviesDomainModel
import com.example.data.toListTopMoviesEntity
import com.example.data.toTitleDomainModel
import com.example.data.toTitleEntity
import com.example.domain.DataSourceRepository
import com.example.domain.UseCaseResponse

class DataSourceRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource,
) : DataSourceRepository {
    override suspend fun getTop250Movies(): UseCaseResponse {
        val response = networkDataSource.getTop250Movies()
        when {
            response.isSuccessful && response.body() != null -> {
                return if (response.body()!!.errorMessage.isEmpty()) {
                    databaseDataSource.insertTopMovies(response.body()!!.items.toListTopMoviesEntity())
                    UseCaseResponse.Success(response.body()!!.items.toListTopMoviesDomainModel())
                } else {
                    UseCaseResponse.Error(response.body()!!.errorMessage)
                }
            }
            response.isSuccessful && response.body() == null -> {
                return UseCaseResponse.Error("response is empty")
            }
            else -> {
                return UseCaseResponse.Error("unknown error")
            }
        }
    }

    override suspend fun getDetails(titleId: String): UseCaseResponse {
        val response = networkDataSource.getDetails(titleId)
        when {
            response.isSuccessful && response.body() != null -> {
                return if (response.body()!!.errorMessage.isNullOrEmpty()) {
                    databaseDataSource.insertTitle(response.body()!!.toTitleEntity())
                    UseCaseResponse.Success(response.body()!!.toTitleDomainModel())
                } else {
                    UseCaseResponse.Error(response.body()!!.errorMessage.toString())
                }
            }
            response.isSuccessful && response.body() == null -> {
                return UseCaseResponse.Error("response is empty")
            }
            else -> {
                return UseCaseResponse.Error("unknown error")
            }
        }
    }
}
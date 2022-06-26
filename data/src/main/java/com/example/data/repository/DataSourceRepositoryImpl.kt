package com.example.data.repository

import com.example.data.toListTopMoviesDomainModel
import com.example.data.toListTopMoviesEntity
import com.example.data.toTitleDomainModel
import com.example.data.toTitleEntity
import com.example.domain.DataSourceRepository
import com.example.domain.UseCaseResponse
import com.example.domain.model.TopMoviesDomainModel

class DataSourceRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource,
) : DataSourceRepository {
    override suspend fun getTop250Movies(isNetworkAvailable: Boolean): UseCaseResponse {
        return try {
            if (isNetworkAvailable) {
                getTopMoviesFromNetworkAndCache()
            } else {
                val cacheTopMovies: List<TopMoviesDomainModel> =
                    databaseDataSource.getAllTopMovies().toListTopMoviesDomainModel()
                when (cacheTopMovies.isEmpty()) {
                    true -> {
                        UseCaseResponse.Error("Network and database is now available")
                    }
                    false -> {
                        UseCaseResponse.Success(cacheTopMovies)
                    }
                }
            }
        } catch (e: Exception) {
            UseCaseResponse.Error(e.message.toString())
        }
    }

    override suspend fun getDetails(isNetworkAvailable: Boolean, titleId: String): UseCaseResponse {
        return try {
            if (isNetworkAvailable) {
                getDetailsFromNetworkAndCache(titleId)
            } else {
                try {
                    UseCaseResponse.Success(databaseDataSource.getTitleById(titleId).toTitleDomainModel())
                }catch (e: NullPointerException){
                    UseCaseResponse.Error("Network and database is now available")
                }catch (e: Exception){
                    UseCaseResponse.Error(e.message.toString())
                }
            }
        } catch (e: Exception) {
            UseCaseResponse.Error(e.message.toString())
        }
    }

    private suspend fun getDetailsFromNetworkAndCache(titleId: String): UseCaseResponse {
        try {
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
        } catch (e: Exception) {
            return UseCaseResponse.Error(e.message.toString())
        }
    }

    private suspend fun getTopMoviesFromNetworkAndCache(): UseCaseResponse {
        try {
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
        } catch (e: Exception) {
            return UseCaseResponse.Error(e.message.toString())
        }
    }
}
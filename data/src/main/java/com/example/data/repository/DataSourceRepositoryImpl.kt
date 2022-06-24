package com.example.data.repository

import com.example.data.getErrorTop250Movies
import com.example.data.toListTopMoviesEntity
import com.example.data.toTopMoviesDomainModel
import com.example.domain.DataSourceRepository
import com.example.domain.model.TopMoviesDomainModel

class DataSourceRepositoryImpl(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
): DataSourceRepository {
    override suspend fun getTop250Movies(): TopMoviesDomainModel {
        val response = networkRepository.getTop250Movies()
        return if (response.isSuccessful && response.body() != null){
            databaseRepository.insert(response.body()!!.items.toListTopMoviesEntity())
            response.body()!!.toTopMoviesDomainModel()
        }else{
            getErrorTop250Movies()
        }
    }
}
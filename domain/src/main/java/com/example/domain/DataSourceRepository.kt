package com.example.domain

import com.example.domain.model.TopMoviesDomainModel

interface DataSourceRepository {

    suspend fun getTop250Movies(): TopMoviesDomainModel
}
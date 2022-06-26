package com.example.domain

interface DataSourceRepository {

    suspend fun getTop250Movies(isNetworkAvailable: Boolean): UseCaseResponse
    suspend fun getDetails(isNetworkAvailable: Boolean, titleId: String): UseCaseResponse
}
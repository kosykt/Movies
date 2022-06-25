package com.example.domain

interface DataSourceRepository {

    suspend fun getTop250Movies(): UseCaseResponse
    suspend fun getDetails(titleId: String): UseCaseResponse
}
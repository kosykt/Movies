package com.example.domain

interface DataSourceRepository {

    suspend fun getTop250Movies(): UseCaseResponse
}
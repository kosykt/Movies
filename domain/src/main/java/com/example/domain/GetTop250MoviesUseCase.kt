package com.example.domain

class GetTop250MoviesUseCase(
    private val repository: DataSourceRepository
) {
    suspend fun execute(isNetworkAvailable: Boolean) = repository.getTop250Movies(isNetworkAvailable)
}
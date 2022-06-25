package com.example.domain

class GetTop250MoviesUseCase(
    private val repository: DataSourceRepository
) {
    suspend fun execute() = repository.getTop250Movies()
}
package com.example.domain

class GetTop250MoviesUseCase(
    private val repository: DataSourceRepository
) {
    suspend fun execute(): UseCaseResponse {
        return repository.getTop250Movies()
    }
}
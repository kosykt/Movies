package com.example.domain

class GetDetailsUseCse(
    private val repository: DataSourceRepository
) {
    suspend fun execute(isNetworkAvailable: Boolean, titleId: String) =
        repository.getDetails(isNetworkAvailable, titleId)
}
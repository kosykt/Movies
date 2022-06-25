package com.example.domain

import com.example.domain.DataSourceRepository

class GetDetailsUseCse(
    private val repository: DataSourceRepository
) {
    suspend fun execute(titleId: String) = repository.getDetails(titleId)
}
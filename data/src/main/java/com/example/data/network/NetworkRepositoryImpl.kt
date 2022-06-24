package com.example.data.network

import com.example.data.network.model.TopMoviesDTO
import com.example.data.repository.NetworkRepository
import retrofit2.Response

class NetworkRepositoryImpl(
    private val retrofitService: RetrofitService
): NetworkRepository {

    override suspend fun getTop250Movies(): Response<TopMoviesDTO> {
        return retrofitService.getTop250Movies()
    }
}
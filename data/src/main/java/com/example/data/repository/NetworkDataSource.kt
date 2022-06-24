package com.example.data.repository

import com.example.data.network.model.TopMoviesDTO
import retrofit2.Response

interface NetworkDataSource {

    suspend fun getTop250Movies(): Response<TopMoviesDTO>
}
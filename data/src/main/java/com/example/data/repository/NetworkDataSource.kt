package com.example.data.repository

import com.example.data.network.model.title.TitleDTO
import com.example.data.network.model.topmovies.TopMoviesDTO
import retrofit2.Response

interface NetworkDataSource {

    suspend fun getTop250Movies(): Response<TopMoviesDTO>
    suspend fun getDetails(titleId: String): Response<TitleDTO>
}
package com.example.data.network

import com.example.data.network.model.title.TitleDTO
import com.example.data.network.model.topmovies.TopMoviesDTO
import com.example.data.repository.NetworkDataSource
import retrofit2.Response

class NetworkDataSourceImpl(
    private val retrofitService: RetrofitService
): NetworkDataSource {

    override suspend fun getTop250Movies(): Response<TopMoviesDTO> {
        return retrofitService.getTop250Movies()
    }

    override suspend fun getDetails(titleId: String): Response<TitleDTO> {
        return retrofitService.getDetails(titleId = titleId)
    }
}
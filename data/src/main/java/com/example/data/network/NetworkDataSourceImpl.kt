package com.example.data.network

import com.example.data.network.model.title.TitleDTO
import com.example.data.network.model.topmovies.TopMoviesDTO
import com.example.data.repository.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class NetworkDataSourceImpl(
    private val retrofitService: RetrofitService
) : NetworkDataSource {

    override suspend fun getTop250Movies(): Response<TopMoviesDTO> {
        return withContext(Dispatchers.IO) { retrofitService.getTop250Movies() }
    }

    override suspend fun getDetails(titleId: String): Response<TitleDTO> {
        return withContext(Dispatchers.IO) { retrofitService.getDetails(titleId = titleId) }
    }
}
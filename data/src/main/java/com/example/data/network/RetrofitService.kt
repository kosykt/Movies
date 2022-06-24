package com.example.data.network

import com.example.data.BuildConfig
import com.example.data.network.model.TopMoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/en/API/Top250Movies/{app_key}")
    suspend fun getTop250Movies(
        @Path("app_key") appId: String = BuildConfig.APP_KEY,
    ): Response<TopMoviesDTO>
}
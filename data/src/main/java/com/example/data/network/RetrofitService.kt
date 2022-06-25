package com.example.data.network

import com.example.data.BuildConfig
import com.example.data.network.model.title.TitleDTO
import com.example.data.network.model.topmovies.TopMoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("/en/API/Top250Movies/{app_key}")
    suspend fun getTop250Movies(
        @Path("app_key") appId: String = BuildConfig.APP_KEY,
    ): Response<TopMoviesDTO>

    @GET("/en/API/Title/{app_key}/{title_id}")
    suspend fun getDetails(
        @Path("app_key") appId: String = BuildConfig.APP_KEY,
        @Path("title_id") titleId: String,
    ): Response<TitleDTO>
}
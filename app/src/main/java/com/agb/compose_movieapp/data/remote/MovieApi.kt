package com.agb.compose_movieapp.data.remote

import com.agb.compose_movieapp.BuildConfig
import com.agb.compose_movieapp.data.remote.dto.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    @GET(ApiConstant.ENDPOINT_GET_POPULAR)
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = ApiConstant.STARTING_PAGE_INDEX,
        @Query("language") language: String = ApiConstant.LANGUAGE,
    ): MovieListResponse


}
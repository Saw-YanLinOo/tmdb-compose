package com.agb.compose_movieapp.domain.repository

import com.agb.compose_movieapp.data.mapper.toMovieVO
import com.agb.compose_movieapp.data.remote.MovieApi
import com.agb.compose_movieapp.domain.model.MovieVO
import javax.inject.Inject

interface MovieRepository {
    suspend fun getPopularMovie(): List<MovieVO>
}

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
) : MovieRepository {

    override suspend fun getPopularMovie(): List<MovieVO> {
        return movieApi.getPopularMovie().results.map {
            it.toMovieVO()
        }
    }
}
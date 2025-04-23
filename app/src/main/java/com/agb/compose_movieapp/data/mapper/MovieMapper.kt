package com.agb.compose_movieapp.data.mapper

import com.agb.compose_movieapp.data.remote.dto.MovieResponse
import com.agb.compose_movieapp.domain.model.MovieVO


fun MovieResponse.toMovieVO(): MovieVO {
    return MovieVO(
        id = id,
        title = title,
        posterRes = "https://image.tmdb.org/t/p/w500$backdropPath",
        rating = voteAverage
    )
}


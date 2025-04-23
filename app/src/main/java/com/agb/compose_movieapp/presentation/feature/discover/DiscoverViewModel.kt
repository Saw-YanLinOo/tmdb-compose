package com.agb.compose_movieapp.presentation.feature.discover

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agb.compose_movieapp.domain.model.MovieVO
import com.agb.compose_movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private var _popularMovies = MutableStateFlow<List<MovieVO>>(listOf())
    val popularMovies: StateFlow<List<MovieVO>> = _popularMovies.asStateFlow()

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() {
        viewModelScope.launch {
            try {
                val result = movieRepository.getPopularMovie()
                _popularMovies.value = result
                Log.d("DiscoverViewModel", "getPopularMovie: $result")
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
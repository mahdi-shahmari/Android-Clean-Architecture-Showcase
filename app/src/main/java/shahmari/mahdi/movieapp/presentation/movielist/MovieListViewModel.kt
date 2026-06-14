package shahmari.mahdi.movieapp.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import shahmari.mahdi.movieapp.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import shahmari.mahdi.movieapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

  //inside status
    private val _state = MutableStateFlow(MovieListState())
    // for use in ui
    val state: StateFlow<MovieListState> = _state.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        // Use Case  for movie first screen
        getPopularMoviesUseCase(page = 1).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MovieListState(movies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MovieListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope) // run main task without block main thread
    }
}
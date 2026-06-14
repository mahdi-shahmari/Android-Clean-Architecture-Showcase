package shahmari.mahdi.movieapp.presentation.movielist

import shahmari.mahdi.movieapp.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)

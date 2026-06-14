package shahmari.mahdi.movieapp.domain.repository

import shahmari.mahdi.movieapp.domain.model.Movie

interface MovieRepository {
    //return movie list as my list
    suspend fun getPopularMovies(page: Int): List<Movie>
}
package shahmari.mahdi.movieapp.data.repository

import shahmari.mahdi.movieapp.BuildConfig
import shahmari.mahdi.movieapp.data.remote.MovieApiService
import shahmari.mahdi.movieapp.data.remote.dto.toMovie
import shahmari.mahdi.movieapp.domain.model.Movie
import shahmari.mahdi.movieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val apiService: MovieApiService
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        // catch data from source
        val responseDto = apiService.getPopularMovies(
            apiKey = BuildConfig.TMDB_API_KEY,
            page = page
        )

        // convert  server data to expected data
        return responseDto.results.map { it.toMovie() }
    }
}
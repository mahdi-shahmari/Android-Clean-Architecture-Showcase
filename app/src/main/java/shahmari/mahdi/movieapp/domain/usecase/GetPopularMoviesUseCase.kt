package shahmari.mahdi.movieapp.domain.usecase

import shahmari.mahdi.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shahmari.mahdi.movieapp.domain.model.Movie
import shahmari.mahdi.movieapp.domain.util.Resource
import java.io.IOException

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(page: Int): Flow<Resource<List<Movie>>> = flow {
        try {
            // send loading status
            emit(Resource.Loading())

            // catch data
            val movies = repository.getPopularMovies(page)

            // send successful data
            emit(Resource.Success(movies))
        } catch (e: IOException) {
            // internet and network error
            emit(Resource.Error("No internet connection. Please check your network."))
        } catch (e: Exception) {
            // other unexpected error
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }
}
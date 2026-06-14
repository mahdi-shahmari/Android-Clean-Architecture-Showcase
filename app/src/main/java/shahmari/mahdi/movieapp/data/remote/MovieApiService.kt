package shahmari.mahdi.movieapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import shahmari.mahdi.movieapp.data.remote.dto.MovieResponseDto

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieResponseDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
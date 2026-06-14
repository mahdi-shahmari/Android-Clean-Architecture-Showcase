package shahmari.mahdi.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shahmari.mahdi.movieapp.data.remote.MovieApiService
import shahmari.mahdi.movieapp.data.repository.MovieRepositoryImpl
import shahmari.mahdi.movieapp.domain.repository.MovieRepository
import shahmari.mahdi.movieapp.domain.usecase.GetPopularMoviesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // این یعنی این نمونه‌ها تا پایان عمر برنامه زنده هستند
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApiService(): MovieApiService {
        return Retrofit.Builder()
            .baseUrl(MovieApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: MovieApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(repository)
    }
}
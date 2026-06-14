package shahmari.mahdi.movieapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import shahmari.mahdi.movieapp.domain.model.Movie
import shahmari.mahdi.movieapp.domain.repository.MovieRepository
import shahmari.mahdi.movieapp.domain.util.Resource
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GetPopularMoviesUseCaseTest {

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var repository: MovieRepository //mock this

    @Before
    fun setUp() {
        repository = mockk()
        getPopularMoviesUseCase = GetPopularMoviesUseCase(repository)
    }

    @Test
    fun `Invoke with successful repository call, emits Loading then Success`() = runTest {
        // make data (Dummy Data)
        val dummyMovies = listOf(
            Movie(id = 1, title = "Inception", overview = "Dream within a dream", imageUrl = "url", rating = 8.8)
        )

        // simulate repository behavior-return data from previews line
        coEvery { repository.getPopularMovies(1) } returns dummyMovies

        // run use case and convert flow to list
        val emissions = getPopularMoviesUseCase(1).toList()

        //  Assert test
        assertThat(emissions[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(emissions[1]).isInstanceOf(Resource.Success::class.java)
        assertThat(emissions[1].data).isEqualTo(dummyMovies)
    }

    @Test
    fun `Invoke with network exception, emits Loading then Error`() = runTest {
        // error simulate
        coEvery { repository.getPopularMovies(1) } throws IOException()

        // run Use Case
        val emissions = getPopularMoviesUseCase(1).toList()

        // error state check
        assertThat(emissions[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(emissions[1]).isInstanceOf(Resource.Error::class.java)
        assertThat(emissions[1].message).isEqualTo("No internet connection. Please check your network.")
    }
}
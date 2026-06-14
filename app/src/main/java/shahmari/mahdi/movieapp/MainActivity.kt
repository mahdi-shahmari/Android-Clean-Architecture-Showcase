package shahmari.mahdi.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shahmari.mahdi.movieapp.data.remote.MovieApiService
import shahmari.mahdi.movieapp.data.repository.MovieRepositoryImpl
import shahmari.mahdi.movieapp.domain.usecase.GetPopularMoviesUseCase
import shahmari.mahdi.movieapp.presentation.movielist.MovieListScreen
import shahmari.mahdi.movieapp.presentation.movielist.MovieListViewModel
import shahmari.mahdi.movieapp.ui.theme.AndroidCleanArchitectureShowcaseTheme
@AndroidEntryPoint // این انوتیشن به هیلت اجازه می‌دهد به این اکتیویتی دیتای لازم را تزریق کند
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // with hiltViewModel hilt automatically find and run
                    val viewModel: MovieListViewModel = hiltViewModel()
                    MovieListScreen(viewModel = viewModel)
                }
            }
        }

    }
}

package shahmari.mahdi.movieapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import shahmari.mahdi.movieapp.domain.model.Movie

data class MovieDto (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double
)
fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        //make absolute image address from TMDB
        imageUrl = "https://image.tmdb.org/t/p/w500$posterPath",
        rating = voteAverage
    )
}
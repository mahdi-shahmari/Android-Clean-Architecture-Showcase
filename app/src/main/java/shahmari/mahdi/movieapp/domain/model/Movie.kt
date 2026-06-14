package shahmari.mahdi.movieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val rating: Double
)

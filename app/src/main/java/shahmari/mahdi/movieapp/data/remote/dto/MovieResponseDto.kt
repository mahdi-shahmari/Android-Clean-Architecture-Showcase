package shahmari.mahdi.movieapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("results") val results: List<MovieDto>
)

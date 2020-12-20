package by.itacademy.tmbdapp.api.data

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    @SerializedName("id") val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    @SerializedName("overview") val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    @SerializedName("release_date") val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    @SerializedName("title") val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val vote_average: Double,
    val vote_count: Int
)
package by.itacademy.tmbdapp.api.moviesapi

import by.itacademy.tmbdapp.api.data.GetMoviesResponse
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "73e49e961534a6224b747edfd1cf36f5"

interface MoviesApi {
    @GET("movie/{category}")
    fun getCategoryMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String?,
    ): Call<GetMoviesResponse>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language:  String,
    ): Call<Movie>
    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ):Call<MovieTrailer>

}
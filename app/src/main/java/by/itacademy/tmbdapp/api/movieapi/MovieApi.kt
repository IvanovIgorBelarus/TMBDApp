package by.itacademy.tmbdapp.api.movieapi

import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer
import by.itacademy.tmbdapp.api.moviesapi.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String,
    ): Call<Movie>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<MovieTrailer>

    @POST("movie/{movie_id}/rating")
    fun rateMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<Int>
}
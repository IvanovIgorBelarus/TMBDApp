package by.itacademy.tmbdapp.api.movieapi

import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer
import by.itacademy.tmbdapp.api.data.RateValueJSON
import by.itacademy.tmbdapp.api.data.SimilarMoviesJSON
import by.itacademy.tmbdapp.api.moviesapi.API_KEY
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String,
    ): Single<Movie>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<MovieTrailer>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String,
    ): Call<SimilarMoviesJSON>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("movie/{movie_id}/rating")
    fun rateMovieAsGuest(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("guest_session_id") session: String?,
        @Body value: RateValueJSON,
    ): Call<ResponseBody>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("movie/{movie_id}/rating")
    fun rateMovieAsUser(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") session: String?,
        @Body value: RateValueJSON,
    ): Call<ResponseBody>

}
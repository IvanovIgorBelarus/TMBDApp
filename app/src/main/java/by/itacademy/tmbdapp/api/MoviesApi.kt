package by.itacademy.tmbdapp.api

import android.telecom.Call
import by.itacademy.tmbdapp.api.model.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "73e49e961534a6224b747edfd1cf36f5",
        @Query("page") page: Int
    ): retrofit2.Call<GetMoviesResponse>
}
package by.itacademy.tmbdapp.api

import by.itacademy.tmbdapp.api.model.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/{category}")
    fun getCategoryMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String = "73e49e961534a6224b747edfd1cf36f5"
    ): retrofit2.Call<GetMoviesResponse>
}
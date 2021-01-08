package by.itacademy.tmbdapp.api.moviesapi

import by.itacademy.tmbdapp.api.data.GetMoviesResponse
import by.itacademy.tmbdapp.api.data.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"
const val AUTHENTICATION_URL = "https://www.themoviedb.org/authenticate/"

object MoviesRepository {
    private val moviesApi: MoviesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        moviesApi = retrofit.create(MoviesApi::class.java)
    }

    fun getCategoryMovies(
        category: String,
        page: Int,
        language: String?,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit,
    ) {
        moviesApi.getCategoryMovies(category, page = page, language = language)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}
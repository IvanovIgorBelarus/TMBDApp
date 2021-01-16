package by.itacademy.tmbdapp.api.moviesapi

import by.itacademy.tmbdapp.api.RetrofitRepository
import by.itacademy.tmbdapp.api.data.GetMoviesResponse
import by.itacademy.tmbdapp.api.data.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MoviesRepository {
    private val moviesApi: MoviesApi =
        RetrofitRepository.getRetrofit().create(MoviesApi::class.java)

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
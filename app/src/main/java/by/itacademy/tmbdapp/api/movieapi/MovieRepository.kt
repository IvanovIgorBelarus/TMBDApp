package by.itacademy.tmbdapp.api.movieapi

import android.util.Log
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"

object MovieRepository {
    private val movieApi: MovieApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieApi = retrofit.create(MovieApi::class.java)
    }

    fun getMovie(
        id: Int,
        language: String,
        onSuccess: (movie: Movie) -> Unit,
        onError: () -> Unit,
    ) {
        movieApi.getMovie(id, language = language)
            .enqueue(object : Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getMovieTrailer(
        id: Int,
        onSuccess: (movieTrailer: MovieTrailer) -> Unit,
        onError: () -> Unit,
    ) {
        movieApi.getMovieTrailer(id = id)
            .enqueue(object : Callback<MovieTrailer> {
                override fun onResponse(
                    call: Call<MovieTrailer>,
                    response: Response<MovieTrailer>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieTrailer>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun rateMovie(
        id: Int,
        onSuccess: (rate: Int) -> Unit,
        onError: () -> Unit,
    ) {
        movieApi.rateMovie(id = id)
            .enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        onSuccess.invoke(response.body()!!)
                    } else {
                        Log.d("qwe", "$response")
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}
package by.itacademy.tmbdapp.api.moviesapi

import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.api.data.GetMoviesResponse
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer
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

    fun getMovie(
        id: Int,
        language: String,
        onSuccess: (movie: Movie) -> Unit,
        onError: () -> Unit,
    ) {
        moviesApi.getMovie(id, language = language)
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
        moviesApi.getMovieTrailer(id = id)
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
        guest_session_id: String = AuthenticationRepository.guest_session_id,
        onSuccess: (rate: Int) -> Unit,
        onError: () -> Unit,
    ) {
        moviesApi.rateMovie(id = id, guest_session_id = guest_session_id)
            .enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful) {
                        onSuccess.invoke(response.body()!!)
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}
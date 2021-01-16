package by.itacademy.tmbdapp.api.movieapi

import by.itacademy.tmbdapp.api.RetrofitRepository
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.api.data.MovieTrailer
import by.itacademy.tmbdapp.api.data.RateValueJSON
import by.itacademy.tmbdapp.api.data.SimilarMoviesJSON
import by.itacademy.tmbdapp.api.data.SimilarResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MovieRepository {
    private val movieApi: MovieApi = RetrofitRepository.getRetrofit().create(MovieApi::class.java)

    fun getMovie(id: Int, language: String) = movieApi.getMovie(id, language = language)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

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

    fun getSimilarMovies(
        id: Int,
        language: String,
        onSuccess: (similarList: List<SimilarResult>?) -> Unit,
        onError: () -> Unit,
    ) {
        movieApi.getSimilarMovies(id = id, language = language)
            .enqueue(object : Callback<SimilarMoviesJSON> {
                override fun onResponse(
                    call: Call<SimilarMoviesJSON>,
                    response: Response<SimilarMoviesJSON>,
                ) {
                    if (response.isSuccessful) {
                        onSuccess.invoke(response.body()?.results)
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<SimilarMoviesJSON>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun rateMovie(
        id: Int,
        rate: Float,
        onSuccess: (rate: Float) -> Unit,
        onError: () -> Unit,
    ) {
        if (AuthenticationRepository.getSessionId() != null) {
            movieApi.rateMovieAsUser(id = id,
                value = RateValueJSON(rate),
                session = AuthenticationRepository.getSessionId())
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>,
                    ) {
                        if (response.isSuccessful) {
                            onSuccess.invoke(rate)
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        onError.invoke()
                    }
                })
        } else {
            movieApi.rateMovieAsGuest(id = id,
                value = RateValueJSON(rate),
                session = AuthenticationRepository.getSessionId())
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>,
                    ) {
                        if (response.isSuccessful) {
                            onSuccess.invoke(rate)
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        onError.invoke()
                    }
                })
        }
    }
}
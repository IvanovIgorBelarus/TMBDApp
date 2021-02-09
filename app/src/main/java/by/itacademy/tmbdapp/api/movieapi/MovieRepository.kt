package by.itacademy.tmbdapp.api.movieapi

import by.itacademy.tmbdapp.api.RetrofitRepository
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.api.data.RateValueJSON
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(private val authenticationRepository: AuthenticationRepository) {
    private val movieApi: MovieApi = RetrofitRepository.getRetrofit().create(MovieApi::class.java)
    fun getMovie(id: Int, language: String) = movieApi.getMovie(id, language = language)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

    fun getMovieTrailer(id: Int) = movieApi.getMovieTrailer(id = id)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

    fun getSimilarMovies(id: Int, language: String) =
        movieApi.getSimilarMovies(id = id, language = language)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    fun rateMovieFromUser(id: Int, rate: Float) = movieApi.rateMovieAsUser(
        id = id,
        value = RateValueJSON(rate),
        session = authenticationRepository.getSessionId()
    ).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

    fun rateMovieFromGuest(id: Int, rate: Float) = movieApi.rateMovieAsGuest(
        id = id,
        value = RateValueJSON(rate),
        session = authenticationRepository.getGuestSessionId()
    ).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}
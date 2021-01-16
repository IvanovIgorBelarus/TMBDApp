package by.itacademy.tmbdapp.api.moviesapi

import by.itacademy.tmbdapp.api.RetrofitRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object MoviesRepository {
    private val moviesApi: MoviesApi =
        RetrofitRepository.getRetrofit().create(MoviesApi::class.java)

    fun getCategoryMovies(
        category: String, page: Int, language: String?,
    ) = moviesApi.getCategoryMovies(
        category = category,
        page = page,
        language = language).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}
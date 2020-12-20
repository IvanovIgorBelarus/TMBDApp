package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.BaseActivity
import by.itacademy.tmbdapp.api.moviesapi.MoviesRepository
import by.itacademy.tmbdapp.api.data.Movie

class MoviePresenterImpl(private val movieActivityListener: MovieActivityListener) : MoviePresenter {

    private fun getMovie(movie: Movie) {
        movieActivityListener.setValue(movie)
    }

    override fun getMovieFromAPI(id: Int) {
        MoviesRepository.getMovie(
            id,
            language = BaseActivity.dLocale.toLanguageTag(),
            ::getMovie,
            ::onError
        )
    }

    private fun onError() {

    }
}
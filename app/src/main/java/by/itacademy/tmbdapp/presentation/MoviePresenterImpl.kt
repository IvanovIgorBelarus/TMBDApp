package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.view.BaseActivity
import by.itacademy.tmbdapp.api.moviesapi.MoviesRepository
import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer

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

    override fun getTrailerFromApi(id: Int) {
       MoviesRepository.getMovieTrailer(
           id,
           ::getMovieTrailer,
           ::onError
       )

    }
    private fun getMovieTrailer(movieTrailer: MovieTrailer){
        movieActivityListener.setTrailer(movieTrailer.results[0].key)
    }


    private fun onError() {
        movieActivityListener.onError()
    }
}
package by.itacademy.tmbdapp.presentation


import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer
import by.itacademy.tmbdapp.api.movieapi.MovieRepository
import by.itacademy.tmbdapp.view.BaseActivity

class MoviePresenterImpl(private val movieActivityListener: MovieActivityListener) :
    MoviePresenter {

    private fun getMovie(movie: Movie) {
        movieActivityListener.setValue(movie)
    }

    override fun getMovieFromAPI(id: Int) {
        MovieRepository.getMovie(
            id,
            language = BaseActivity.dLocale.toLanguageTag(),
            ::getMovie,
            ::onError
        )
    }

    override fun getTrailerFromApi(id: Int) {
        MovieRepository.getMovieTrailer(
            id,
            ::getMovieTrailer,
            ::onError
        )
    }

    override fun rateMovie(id: Int) {
        MovieRepository.rateMovie(
            id,
            ::doRate,
            ::onError
        )
    }

    private fun getMovieTrailer(movieTrailer: MovieTrailer) {
        movieActivityListener.setTrailer(movieTrailer.results[0].key)
    }

    private fun doRate() {
        movieActivityListener.doRate()
    }


    private fun onError() {
        movieActivityListener.onError()
    }
}
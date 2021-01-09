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
            onSuccess = ::getMovie,
            onError = ::onError
        )
    }

    override fun getTrailerFromApi(id: Int) {
        MovieRepository.getMovieTrailer(
            id,
            onSuccess = ::getMovieTrailer,
            onError = ::onError
        )
    }

    override fun rateMovie(id: Int, rate:Float) {
        MovieRepository.rateMovie(
            id,
            rate,
            onSuccess = ::doRate,
            onError = ::onError
        )
    }

    private fun getMovieTrailer(movieTrailer: MovieTrailer) {
        movieActivityListener.setTrailer(movieTrailer.results[0].key)
    }

    private fun doRate(rate: Float) {
        movieActivityListener.doRate(rate)
    }


    private fun onError() {
        movieActivityListener.onError()
    }
}
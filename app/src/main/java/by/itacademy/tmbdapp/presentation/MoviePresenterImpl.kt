package by.itacademy.tmbdapp.presentation


import by.itacademy.tmbdapp.api.data.Movie
import by.itacademy.tmbdapp.api.data.MovieTrailer
import by.itacademy.tmbdapp.api.data.SimilarResult
import by.itacademy.tmbdapp.api.movieapi.MovieRepository
import by.itacademy.tmbdapp.uimodel.UIMovieModel
import by.itacademy.tmbdapp.uimodelmapper.FeedItemMapper
import by.itacademy.tmbdapp.uimodelmapper.OverViewMapper
import by.itacademy.tmbdapp.uimodelmapper.SimilarMoviesMapper
import by.itacademy.tmbdapp.view.BaseActivity

class MoviePresenterImpl(
    private val movieActivityListener: MovieActivityListener,
    private val feedItemMapper: FeedItemMapper = FeedItemMapper(),
    private val overViewMapper: OverViewMapper = OverViewMapper(),
    private val similarMoviesMapper: SimilarMoviesMapper = SimilarMoviesMapper(),
) :
    MoviePresenter {
    private var similarResultList: MutableList<SimilarResult>? = null

    private fun getMovie(movie: Movie) {
        val list = mutableListOf<UIMovieModel>()
        list.add(feedItemMapper.invoke(movie))
        list.add(overViewMapper.invoke(movie))
        list.add(similarMoviesMapper.invoke(similarResultList))
        movieActivityListener.setValue(list.toList())
    }

    override fun getMovieFromAPI(id: Int) {
        MovieRepository.getMovie(
            id,
            language = BaseActivity.dLocale.toLanguageTag(),
            onSuccess = ::getMovie,
            onError = ::onError
        )
        getSimilarMoviesFromAPI(id)
    }

    private fun getSimilarMoviesFromAPI(id: Int) {
        MovieRepository.getSimilarMovies(
            id,
            language = BaseActivity.dLocale.toLanguageTag(),
            onSuccess = ::getList,
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

    override fun rateMovie(id: Int, rate: Float) {
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

    private fun getList(similarList: List<SimilarResult>?) {
        similarResultList = similarList?.toMutableList()
    }

    private fun onError() {
        movieActivityListener.onError()
    }

}
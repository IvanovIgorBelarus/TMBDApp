package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.api.movieapi.MovieRepository
import by.itacademy.tmbdapp.uimodel.UIMovieModel
import by.itacademy.tmbdapp.uimodel.uimodelmapper.FactsMapper
import by.itacademy.tmbdapp.uimodel.uimodelmapper.FeedItemMapper
import by.itacademy.tmbdapp.uimodel.uimodelmapper.OverViewMapper
import by.itacademy.tmbdapp.uimodel.uimodelmapper.SimilarMoviesMapper
import by.itacademy.tmbdapp.view.BaseActivity

class MoviePresenterImpl(
    private val movieActivityListener: MovieActivityListener,
    private val feedItemMapper: FeedItemMapper,
    private val overViewMapper: OverViewMapper,
    private val similarMoviesMapper: SimilarMoviesMapper,
    private val factsMapper: FactsMapper,
    private val movieRepository: MovieRepository,
    private val authenticationRepository: AuthenticationRepository
) :
    MoviePresenter {
    private val list = mutableListOf<UIMovieModel>()

    override fun getMovieFromAPI(id: Int) {
        movieRepository.getMovie(id, BaseActivity.dLocale.toLanguageTag())
            .subscribe { result ->
                list.add(feedItemMapper.invoke(result))
                list.add(overViewMapper.invoke(result))
                list.add(factsMapper.invoke(result))
                movieActivityListener.setValue(list.toList())
            }
    }

    override fun getSimilarMoviesFromAPI(id: Int) {
        movieRepository.getSimilarMovies(id,
            language = BaseActivity.dLocale.toLanguageTag())
            .subscribe { result ->
                if (result.results.isNotEmpty()) {
                    list.add(similarMoviesMapper.invoke(result.results.toMutableList()))
                    movieActivityListener.setValue(list.toList())
                }
            }
    }

    override fun getTrailerFromApi(id: Int) {
        movieRepository.getMovieTrailer(id)
            .subscribe { movieTrailer ->
                if (movieTrailer.results.isNotEmpty()) {
                    movieActivityListener.setTrailer(movieTrailer.results[0].key)
                }
            }
    }

    override fun rateMovie(id: Int, rate: Float) {
        if (authenticationRepository.getSessionId() != null) {
            movieRepository.rateMovieFromUser(id, rate)
                .subscribe { _ -> movieActivityListener.doRate() }
        } else {
            movieRepository.rateMovieFromGuest(id, rate)
                .subscribe { _ -> movieActivityListener.doRate() }
        }
    }
}
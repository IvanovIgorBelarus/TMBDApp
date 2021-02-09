package by.itacademy.tmbdapp.di

import androidx.recyclerview.widget.RecyclerView
import by.itacademy.tmbdapp.api.accountapi.AccountRepository
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.api.movieapi.MovieRepository
import by.itacademy.tmbdapp.api.moviesapi.MoviesRepository
import by.itacademy.tmbdapp.model.AccountDetailsMapper
import by.itacademy.tmbdapp.model.AccountModelMapper
import by.itacademy.tmbdapp.model.AccountRatePathMapper
import by.itacademy.tmbdapp.presentation.AccountActivityListener
import by.itacademy.tmbdapp.presentation.AccountPresenterImpl
import by.itacademy.tmbdapp.presentation.AuthenticationActivityListener
import by.itacademy.tmbdapp.presentation.AuthenticationActivityPresenterImpl
import by.itacademy.tmbdapp.presentation.ListItemActionListener
import by.itacademy.tmbdapp.presentation.MovieActivityListener
import by.itacademy.tmbdapp.presentation.MoviePresenter
import by.itacademy.tmbdapp.presentation.MoviePresenterImpl
import by.itacademy.tmbdapp.presentation.MoviesPresenterImpl
import by.itacademy.tmbdapp.presentation.adapters.CategoryAdapter
import by.itacademy.tmbdapp.presentation.adapters.MovieAdapter
import by.itacademy.tmbdapp.presentation.adapters.PosterAdapter
import by.itacademy.tmbdapp.uimodel.uimodelmapper.FactsMapper
import by.itacademy.tmbdapp.uimodel.uimodelmapper.FeedItemMapper
import by.itacademy.tmbdapp.uimodel.uimodelmapper.OverViewMapper
import by.itacademy.tmbdapp.uimodel.uimodelmapper.SimilarMoviesMapper
import org.koin.dsl.module

val movieModule = module {
    single { FeedItemMapper() }
    single { OverViewMapper() }
    single { SimilarMoviesMapper() }
    single { FactsMapper() }
    single { MovieRepository(get()) }
    factory { (movieActivityListener: MovieActivityListener) ->
        MoviePresenterImpl(movieActivityListener, get(), get(), get(), get(), get(), get())
    }
    factory { (moviePresenter: MoviePresenter) -> MovieAdapter(moviePresenter) }
}
val accountModule = module {
    single { AccountModelMapper() }
    single { AccountRatePathMapper() }
    single { AccountDetailsMapper() }
    single { AccountRepository() }
    factory { (accountActivityListener: AccountActivityListener) -> AccountPresenterImpl(accountActivityListener, get(), get(), get(), get(),get()) }
    factory { PosterAdapter() }
}
val authModule = module {
    single { AuthenticationRepository() }
    factory { (listener: AuthenticationActivityListener) -> AuthenticationActivityPresenterImpl(listener, get()) }
}

val moviesModule = module {
    single { MoviesRepository() }
    single { (listener: ListItemActionListener) -> CategoryAdapter(listener) }
    factory { (categoryFragment: String, recycler: RecyclerView) -> MoviesPresenterImpl(categoryFragment, recycler, get(), get()) }
}
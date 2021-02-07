package by.itacademy.tmbdapp.di

import by.itacademy.tmbdapp.model.AccountModelMapper
import by.itacademy.tmbdapp.model.AccountRatePathMapper
import by.itacademy.tmbdapp.presentation.AccountActivityListener
import by.itacademy.tmbdapp.presentation.AccountPresenterImpl
import by.itacademy.tmbdapp.presentation.MovieActivityListener
import by.itacademy.tmbdapp.presentation.MoviePresenter
import by.itacademy.tmbdapp.presentation.MoviePresenterImpl
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
    factory { (movieActivityListener: MovieActivityListener) ->
        MoviePresenterImpl(movieActivityListener, get(), get(), get(), get())
    }
    factory { (moviePresenter: MoviePresenter) -> MovieAdapter(moviePresenter) }
}
val accountModule = module {
    single { AccountModelMapper() }
    single { AccountRatePathMapper() }
    factory { (accountActivityListener: AccountActivityListener) -> AccountPresenterImpl(accountActivityListener, get(), get()) }
    factory { PosterAdapter() }
}
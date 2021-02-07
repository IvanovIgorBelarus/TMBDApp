package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.accountapi.AccountRepository
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.model.AccountModelMapper
import by.itacademy.tmbdapp.model.AccountRatePathMapper
import by.itacademy.tmbdapp.presentation.adapters.PosterAdapter
import io.reactivex.android.schedulers.AndroidSchedulers

class AccountPresenterImpl(
    private val accountActivityListener: AccountActivityListener,
    private val accountModelMapper: AccountModelMapper,
    private val accountRatePathMapper: AccountRatePathMapper
) : AccountPresenter {
    override fun getAccountDetailsApi(sessionId: String?) {
        AccountRepository.getAccountDetails(sessionId)
            .map { item -> accountModelMapper.invoke(item) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                accountActivityListener.getDetails(result)
            }

    }

    override fun getAccountRatedMoviesList(sessionId: String?, posterAdapter: PosterAdapter) {
        AccountRepository.getAccountRatedMovies(AuthenticationRepository.getSessionId())
            .map { item -> accountRatePathMapper.invoke(item.results) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                posterAdapter.update(result)
            }
    }
}
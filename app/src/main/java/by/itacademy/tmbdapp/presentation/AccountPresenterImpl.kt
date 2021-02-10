package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.accountapi.AccountRepository
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.model.AccountDetailsMapper
import by.itacademy.tmbdapp.model.AccountModelMapper
import by.itacademy.tmbdapp.model.AccountRatePathMapper
import by.itacademy.tmbdapp.presentation.adapters.PosterAdapter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

class AccountPresenterImpl(
    private val accountActivityListener: AccountActivityListener,
    private val accountModelMapper: AccountModelMapper,
    private val accountRatePathMapper: AccountRatePathMapper,
    private val accountDetailsMapper: AccountDetailsMapper,
    private val accountRepository: AccountRepository,
    private val authenticationRepository: AuthenticationRepository,
) : AccountPresenter {
    override fun getAccountDetailsApi(sessionId: String?, posterAdapter: PosterAdapter) {
        val accountDetails = accountRepository.getAccountDetails(sessionId)
            .map { item -> accountModelMapper.invoke(item) }
        val accountRatedMovies = accountRepository.getAccountRatedMovies(authenticationRepository.getSessionId())
            .map { item -> accountRatePathMapper.invoke(item.results) }
        Single.zip(accountDetails, accountRatedMovies) { d, r -> accountDetailsMapper.invoke(d, r) }
            .observeOn(AndroidSchedulers.mainThread()).subscribe { result ->
                accountActivityListener.getDetails(result.model)
                posterAdapter.update(result.list)
            }
    }
}
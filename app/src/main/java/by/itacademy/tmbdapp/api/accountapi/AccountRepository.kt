package by.itacademy.tmbdapp.api.accountapi

import by.itacademy.tmbdapp.api.RetrofitRepository
import io.reactivex.schedulers.Schedulers

class AccountRepository {
    private val accountApi: AccountApi =
        RetrofitRepository.getRetrofit().create(AccountApi::class.java)


    fun getAccountDetails(sessionId: String?) = accountApi.getAccountDetails(sessionId = sessionId)
        .subscribeOn(Schedulers.newThread())


    fun getAccountRatedMovies(sessionId: String?) =
        accountApi.getAccountRatedMovies(sessionId = sessionId)
            .subscribeOn(Schedulers.newThread())
}




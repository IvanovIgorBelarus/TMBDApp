package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.accountapi.AccountRepository
import by.itacademy.tmbdapp.api.data.Account
import by.itacademy.tmbdapp.model.AccountModelMapper

class AccountPresenterImpl(
    private val accountActivityListener: AccountActivityListener,
    private val accountModelMapper: AccountModelMapper = AccountModelMapper(),

    ) : AccountPresenter {
    override fun getAccountDetailsApi(sessionId: String?) {
        AccountRepository.getAccountDetails(
            sessionId,
            onSuccess = ::getDetails,
            onError = ::onError
        )
    }

    private fun getDetails(account: Account?) {
        accountActivityListener.getDetails(accountModelMapper.invoke(account))
    }

    private fun onError() {}

}
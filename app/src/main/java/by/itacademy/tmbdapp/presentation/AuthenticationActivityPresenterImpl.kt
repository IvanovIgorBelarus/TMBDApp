package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.api.data.UsersDataJSON

class AuthenticationActivityPresenterImpl(
    private val authenticationActivityListener: AuthenticationActivityListener,
) : AuthenticationActivityPresenter {

    override fun createSessionWithLoginFromApi(userDataJSON: UsersDataJSON) {
        AuthenticationRepository.createSessionWithLogin(
            userDataJSON,
            onSuccess = ::createSessionWithLogin,
            onError = ::onError
        )
    }

    override fun getRequestTokenFromApi() {
        AuthenticationRepository.getRequestToken()
    }

    private fun createSessionWithLogin(isCreate: Boolean?) {
        authenticationActivityListener.createSessionWithLogin(isCreate)
    }

    private fun onError() {
    }
}
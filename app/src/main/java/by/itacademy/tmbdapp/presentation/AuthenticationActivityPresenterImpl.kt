package by.itacademy.tmbdapp.presentation

import android.util.Log
import by.itacademy.tmbdapp.api.data.Authentication
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository

class AuthenticationActivityPresenterImpl(
    private val authenticationActivityListener: AuthenticationActivityListener,
): AuthenticationActivityPresenter {
    override fun getRequestTokenFromApi() {
        AuthenticationRepository.getRequestToken(
            onSuccess = ::getRequestToken,
            onError = ::onError
        )
    }

    private fun getRequestToken(authentication: Authentication) {
        authenticationActivityListener.showRequestToken(authentication.request_token)
    }

    private fun onError() {
        Log.d("1", "Error getRequestToken")
    }
}
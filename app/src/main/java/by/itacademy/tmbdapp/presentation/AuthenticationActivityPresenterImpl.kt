package by.itacademy.tmbdapp.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
        AuthenticationRepository.getRequestToken(
            onSuccess = ::getRequestToken,
            onError = ::onError
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRequestToken(token: String?) {
    }

    private fun createSessionWithLogin(isCreate: Boolean?) {
        authenticationActivityListener.createSessionWithLogin(isCreate)
    }

    private fun onError() {
        Log.d("1", "Error getRequestToken")
    }
}
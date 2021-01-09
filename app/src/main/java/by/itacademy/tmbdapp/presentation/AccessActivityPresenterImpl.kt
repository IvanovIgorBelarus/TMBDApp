package by.itacademy.tmbdapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import by.itacademy.tmbdapp.api.authenticationapi.AuthenticationRepository
import by.itacademy.tmbdapp.view.AccessActivity

class AccessActivityPresenterImpl(private val accessActivityListener: AccessActivity) :
    AccessActivityPresenter {
    override fun getRequestTokenFromApi() {
        AuthenticationRepository.getRequestToken(
            onSuccess = ::getRequestToken,
            onError = ::onError
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRequestToken(token: String?) {
        accessActivityListener.showRequestToken(token)
    }

    private fun onError() {}
}
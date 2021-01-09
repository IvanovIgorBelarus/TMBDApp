package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.api.data.UsersDataJSON

interface AuthenticationActivityPresenter {
    fun createSessionWithLoginFromApi(userDataJSON: UsersDataJSON)
    fun getRequestTokenFromApi()
}
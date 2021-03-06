package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.presentation.adapters.PosterAdapter

interface AccountPresenter {
    fun getAccountDetailsApi(sessionId: String?, posterAdapter: PosterAdapter)
}
package by.itacademy.tmbdapp.presentation

import by.itacademy.tmbdapp.model.AccountModel

interface AccountActivityListener {
    fun getDetails(accountModel: AccountModel)
}
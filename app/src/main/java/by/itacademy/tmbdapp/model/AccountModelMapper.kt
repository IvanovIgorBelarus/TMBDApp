package by.itacademy.tmbdapp.model

import by.itacademy.tmbdapp.api.data.Account

class AccountModelMapper : (Account?) -> AccountModel {
    override fun invoke(account: Account?) = AccountModel(
        avatar = "https://secure.gravatar.com/avatar/${account!!.avatar.gravatar.hash}.jpg?s=64",
        id = account!!.id,
        name = account!!.name,
        username = account!!.username
    )
}
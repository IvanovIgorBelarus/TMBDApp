package by.itacademy.tmbdapp.model

class AccountDetailsMapper: (AccountModel, List<String>) -> AccountDetailsModel {
    override fun invoke(model: AccountModel, list: List<String>) = AccountDetailsModel(model, list)

}
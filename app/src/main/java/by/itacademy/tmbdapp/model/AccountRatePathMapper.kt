package by.itacademy.tmbdapp.model

import by.itacademy.tmbdapp.api.data.AccountRatedMoviesResult

class AccountRatePathMapper:(List<AccountRatedMoviesResult>)-> List<String> {
    override fun invoke(list: List<AccountRatedMoviesResult>): List<String> =list.map { item->"https://image.tmdb.org/t/p/w342${item.poster_path}" }
}
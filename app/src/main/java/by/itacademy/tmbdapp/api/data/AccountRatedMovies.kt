package by.itacademy.tmbdapp.api.data

data class AccountRatedMovies(
    val page: Int,
    val results: List<AccountRatedMoviesResult>,
    val total_pages: Int,
    val total_results: Int,
)
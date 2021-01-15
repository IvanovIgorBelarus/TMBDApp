package by.itacademy.tmbdapp.api.accountapi


import by.itacademy.tmbdapp.api.data.Account
import by.itacademy.tmbdapp.api.data.AccountRatedMovies
import by.itacademy.tmbdapp.api.moviesapi.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountApi {
    @GET("account")
    fun getAccountDetails(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String?,
    ): Single<Account>

    @GET("account/{account_id}/rated/movies")
    fun getAccountRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String?,
    ): Single<AccountRatedMovies>
}
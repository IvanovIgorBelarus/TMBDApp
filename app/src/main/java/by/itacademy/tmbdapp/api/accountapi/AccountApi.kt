package by.itacademy.tmbdapp.api.accountapi


import by.itacademy.tmbdapp.api.data.Account
import by.itacademy.tmbdapp.api.data.AccountRatedMovies
import by.itacademy.tmbdapp.api.moviesapi.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountApi {
    @GET("account")
    fun getAccountDetails(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String?,
    ): Call<Account>

    @GET("account/{account_id}/rated/movies")
    fun getAccountRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("session_id") sessionId: String?,
    ): Call<AccountRatedMovies>
}
package by.itacademy.tmbdapp.api.accountapi

import by.itacademy.tmbdapp.api.data.Account
import by.itacademy.tmbdapp.api.data.AccountRatedMovies
import by.itacademy.tmbdapp.api.movieapi.BASE_URL
import by.itacademy.tmbdapp.model.AccountRatePathMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AccountRepository {
    private val accountApi: AccountApi
    lateinit var accountRateList: MutableList<String>

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        accountApi = retrofit.create(AccountApi::class.java)
    }

    fun getAccountDetails(
        sessionId: String?,
        onSuccess: (Account?) -> Unit,
        onError: () -> Unit,
    ) {
        accountApi.getAccountDetails(sessionId = sessionId)
            .enqueue(object : Callback<Account> {
                override fun onResponse(call: Call<Account>, response: Response<Account>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            onSuccess.invoke(response.body())
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<Account>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getAccountRatedMovies(
        sessionId: String?,
    ) {
        accountApi.getAccountRatedMovies(sessionId = sessionId)
            .enqueue(object : Callback<AccountRatedMovies> {
                override fun onResponse(
                    call: Call<AccountRatedMovies>,
                    response: Response<AccountRatedMovies>,
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            accountRateList =
                                AccountRatePathMapper().invoke(response.body()!!.results)
                                    .toMutableList()
                        }
                    }
                }

                override fun onFailure(call: Call<AccountRatedMovies>, t: Throwable) {
                }
            })

    }
}
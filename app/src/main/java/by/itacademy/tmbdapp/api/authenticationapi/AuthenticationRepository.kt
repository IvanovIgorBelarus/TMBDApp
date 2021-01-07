package by.itacademy.tmbdapp.api.authenticationapi

import by.itacademy.tmbdapp.api.data.Authentication
import by.itacademy.tmbdapp.api.data.GuestSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthenticationRepository {
    private val authenticationApi: AuthenticationApi
    lateinit var guest_session_id: String
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        authenticationApi = retrofit.create(AuthenticationApi::class.java)
    }

    fun getRequestToken(
        onSuccess: (authentication: Authentication) -> Unit,
        onError: () -> Unit,
    ) {
        authenticationApi.createRequestToken()
            .enqueue(object : Callback<Authentication> {
                override fun onResponse(
                    call: Call<Authentication>,
                    response: Response<Authentication>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<Authentication>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun createGuestSession(
        onSuccess: Unit,
        onError: (Throwable) -> Unit,
    ) {
        authenticationApi.createGuestSession()
            .enqueue(object : Callback<GuestSession> {
                override fun onResponse(
                    call: Call<GuestSession>,
                    response: Response<GuestSession>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            guest_session_id=responseBody.guest_session_id
                            onSuccess
                        }
                    }
                }

                override fun onFailure(call: Call<GuestSession>, t: Throwable) {
                    onError.invoke(t)
                }
            })
    }
}
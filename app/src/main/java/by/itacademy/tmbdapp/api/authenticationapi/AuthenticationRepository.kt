package by.itacademy.tmbdapp.api.authenticationapi

import android.util.Log
import by.itacademy.tmbdapp.api.data.AuthenticationJSON
import by.itacademy.tmbdapp.api.data.AuthenticationResponseJSON
import by.itacademy.tmbdapp.api.data.GuestSession
import by.itacademy.tmbdapp.api.data.SessionResponseJSON
import by.itacademy.tmbdapp.api.data.UsersDataJSON

import by.itacademy.tmbdapp.api.moviesapi.BASE_URL
import by.itacademy.tmbdapp.fragments.TAG
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthenticationRepository {
    private val authenticationApi: AuthenticationApi
    var requestToken: String? = null
    var guest_session_id: String? = null
    var session_id: String? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        authenticationApi = retrofit.create(AuthenticationApi::class.java)
    }

    fun getRequestToken(
        onSuccess: (token:String?) -> Unit,
        onError: () -> Unit,
    ) {
        authenticationApi.createRequestToken()
            .enqueue(object : Callback<AuthenticationJSON> {
                override fun onResponse(
                    call: Call<AuthenticationJSON>,
                    response: Response<AuthenticationJSON>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            requestToken = responseBody.request_token
                            Log.d(TAG, "createRequestToken: requestToken=$requestToken")
                            onSuccess.invoke(requestToken)
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<AuthenticationJSON>, t: Throwable) {
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
                            guest_session_id = responseBody.guest_session_id
                            onSuccess
                        }
                    }
                }

                override fun onFailure(call: Call<GuestSession>, t: Throwable) {
                    onError.invoke(t)
                }
            })
    }

    fun createSessionWithLogin(
        userDataJSON: UsersDataJSON,
        onSuccess: (success: Boolean?) -> Unit,
        onError: () -> Unit,
    ) {
        authenticationApi.createSessionWithLogin(usersDataJSON = userDataJSON)
            .enqueue(object : Callback<AuthenticationResponseJSON> {
                override fun onResponse(
                    call: Call<AuthenticationResponseJSON>,
                    response: Response<AuthenticationResponseJSON>,
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        Log.d(TAG,"createSessionWithLogin: $responseBody")
                        onSuccess.invoke(responseBody?.success)
                    } else {
                        Log.d(TAG,"createSessionWithLogin fail: $response")
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<AuthenticationResponseJSON>, t: Throwable) {
                    Log.d(TAG,"createSessionWithLogin fail: $t")
                    onError.invoke()
                }
            })
    }

    fun createSession(
    ) {
        authenticationApi.createSession(value = requestToken)
            .enqueue(object : Callback<SessionResponseJSON> {
                override fun onResponse(
                    call: Call<SessionResponseJSON>,
                    response: Response<SessionResponseJSON>,
                ) {
                    Log.d(TAG, "createSession: requestSessionToken=$requestToken")
                    if (response.isSuccessful) {
                        session_id = response.body()?.session_id
                        Log.d(TAG, "session_id=$session_id")
                    } else {
                        Log.d(TAG, "session is fail $response")
                    }
                }

                override fun onFailure(call: Call<SessionResponseJSON>, t: Throwable) {
                }
            })
    }
}
package by.itacademy.tmbdapp.api.authenticationapi

import by.itacademy.tmbdapp.api.RetrofitRepository
import by.itacademy.tmbdapp.api.data.AuthenticationResponseJSON
import by.itacademy.tmbdapp.api.data.GuestSession
import by.itacademy.tmbdapp.api.data.SessionJSON
import by.itacademy.tmbdapp.api.data.SessionResponseJSON
import by.itacademy.tmbdapp.api.data.UsersDataJSON
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AuthenticationRepository {
    private val authenticationApi: AuthenticationApi = RetrofitRepository.getRetrofit().create(AuthenticationApi::class.java)
    var requestToken: String? = null
    var guestSessionId: String? = null
    var sessionId: String? = null

    fun getRequestToken() = authenticationApi.createRequestToken()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { result -> requestToken = result.request_token }

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
                            guestSessionId = responseBody.guest_session_id
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
                        onSuccess.invoke(responseBody?.success)
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<AuthenticationResponseJSON>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun createSession(
    ) {
        authenticationApi.createSession(value = SessionJSON(requestToken))
            .enqueue(object : Callback<SessionResponseJSON> {
                override fun onResponse(
                    call: Call<SessionResponseJSON>,
                    response: Response<SessionResponseJSON>,
                ) {
                    if (response.isSuccessful) {
                        sessionId = response.body()?.session_id
                    }
                }

                override fun onFailure(call: Call<SessionResponseJSON>, t: Throwable) {
                }
            })
    }
}
package by.itacademy.tmbdapp.api.authenticationapi


import by.itacademy.tmbdapp.api.RetrofitRepository
import by.itacademy.tmbdapp.api.data.SessionJSON
import by.itacademy.tmbdapp.api.data.UsersDataJSON
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthenticationRepository {
    private val authenticationApi: AuthenticationApi =
        RetrofitRepository.getRetrofit().create(AuthenticationApi::class.java)
    private var requestToken: String? = null
    private var guestSessionId: String? = null
    private var sessionId: String? = null
    fun getGuestSessionId() = guestSessionId
    fun getSessionId() = sessionId
    fun getRequestToken(): String? {
        authenticationApi.createRequestToken()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> requestToken = result.request_token }
        return requestToken
    }

    fun createGuestSessionId() {
        authenticationApi.createGuestSession()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                guestSessionId = result.guest_session_id
            }
    }

    fun createSessionWithLogin(userDataJSON: UsersDataJSON) =
        authenticationApi.createSessionWithLogin(usersDataJSON = userDataJSON)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    fun createSessionId() {
        authenticationApi.createSession(value = SessionJSON(requestToken))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                sessionId = result.session_id
            }
    }
}


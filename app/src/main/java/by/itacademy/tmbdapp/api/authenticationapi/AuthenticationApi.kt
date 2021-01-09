package by.itacademy.tmbdapp.api.authenticationapi


import by.itacademy.tmbdapp.api.data.AuthenticationJSON
import by.itacademy.tmbdapp.api.data.AuthenticationResponseJSON
import by.itacademy.tmbdapp.api.data.GuestSession
import by.itacademy.tmbdapp.api.data.SessionResponseJSON
import by.itacademy.tmbdapp.api.data.UsersDataJSON
import by.itacademy.tmbdapp.api.moviesapi.API_KEY
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationApi {
    @GET("authentication/token/new")
    fun createRequestToken(
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<AuthenticationJSON>

    @GET("authentication/guest_session/new")
    fun createGuestSession(
        @Query("api_key") apiKey: String = API_KEY,
    ): Call<GuestSession>

    @POST("authentication/token/validate_with_login")
    fun createSessionWithLogin(
        @Query("api_key") apiKey: String = API_KEY,
        @Body usersDataJSON: UsersDataJSON,
    ): Call<AuthenticationResponseJSON>

    @POST("authentication/session/new")
    fun createSession(
        @Query("api_key") apiKey: String = API_KEY,
        @Body value: String?
    ): Call<SessionResponseJSON>
}
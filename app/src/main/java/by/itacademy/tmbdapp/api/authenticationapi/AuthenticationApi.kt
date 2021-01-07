package by.itacademy.tmbdapp.api.authenticationapi

import by.itacademy.tmbdapp.api.data.Authentication
import by.itacademy.tmbdapp.api.data.GuestSession
import by.itacademy.tmbdapp.api.moviesapi.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationApi {
    @GET("authentication/token/new")
    fun createRequestToken(
        @Query("api_key") apiKey: String = API_KEY
    ): Call<Authentication>

    @GET("authentication/guest_session/new")
    fun createGuestSession(
        @Query("api_key") apiKey: String = API_KEY
    ):Call<GuestSession>

    @POST("authentication/token/validate_with_login")
    fun createSessionWithLogin(
        @Query("api_key") apiKey: String = API_KEY
    )
}
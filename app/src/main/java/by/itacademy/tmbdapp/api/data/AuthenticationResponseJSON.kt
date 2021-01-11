package by.itacademy.tmbdapp.api.data

data class AuthenticationResponseJSON(
    val expires: String,
    val request_token: String,
    val success: Boolean,
)
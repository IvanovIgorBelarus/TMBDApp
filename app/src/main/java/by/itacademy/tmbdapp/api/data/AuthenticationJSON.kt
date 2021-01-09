package by.itacademy.tmbdapp.api.data

data class AuthenticationJSON(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)
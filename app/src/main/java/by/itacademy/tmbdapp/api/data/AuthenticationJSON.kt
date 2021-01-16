package by.itacademy.tmbdapp.api.data

data class AuthenticationJSON(
    val expires: String,
    val request_token: String,
    val success: Boolean,
)
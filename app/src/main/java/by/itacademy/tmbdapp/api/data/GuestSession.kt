package by.itacademy.tmbdapp.api.data

data class GuestSession(
    val expires_at: String,
    val guest_session_id: String,
    val success: Boolean
)
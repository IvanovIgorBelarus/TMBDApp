package by.itacademy.tmbdapp.api.data

import com.google.gson.annotations.SerializedName

class UsersDataJSON(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val request_token: String,
)
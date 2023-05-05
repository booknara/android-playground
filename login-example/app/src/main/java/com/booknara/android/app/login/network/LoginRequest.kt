package com.booknara.android.app.login.network

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    val password: String
)

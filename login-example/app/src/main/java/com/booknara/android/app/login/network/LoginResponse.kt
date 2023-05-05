package com.booknara.android.app.login.network

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: Data
) {
    data class Data (
        @SerializedName("id")
        var id: String,
        @SerializedName("Id")
        var id2: Int,
        @SerializedName("Email")
        var email: String,
        @SerializedName("Name")
        var name: String,
        @SerializedName("Token")
        var token: String
    )    
}

/**
"data": {
"$id": "2",
"Id": 7075,
"Name": "Developer",
"Email": "Developer5@gmail.com",
"Token": "02b869e4-ea45-4b5c-b764-642a39e95bb7"
}

 */

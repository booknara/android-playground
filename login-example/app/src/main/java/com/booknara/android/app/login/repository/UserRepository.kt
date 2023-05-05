package com.booknara.android.app.login.repository

import com.booknara.android.app.login.network.LoginRequest
import com.booknara.android.app.login.network.LoginResponse
import com.booknara.android.app.login.network.UserApi
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return UserApi.getApi()?.loginUser(loginRequest)
    }
}

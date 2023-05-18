package com.booknara.android.app.retrofit.network

import okhttp3.Interceptor
import okhttp3.Response

class CustomHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("x-custom-header", "value")
            .build()
        return chain.proceed(request)
    }
}

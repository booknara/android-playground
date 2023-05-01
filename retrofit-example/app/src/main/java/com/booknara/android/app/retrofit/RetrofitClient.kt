package com.booknara.android.app.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val heroApi: HeroApi
    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() 
        heroApi = retrofit.create(HeroApi::class.java)
    }
}

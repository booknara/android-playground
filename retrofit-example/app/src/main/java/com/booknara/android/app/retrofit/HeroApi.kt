package com.booknara.android.app.retrofit

import retrofit2.Call
import retrofit2.http.GET

const val BASE_SERVER_URL = "https://simplifiedcoding.net/demos/"

interface HeroApi {
    @GET("marvel")
    fun getHeroes(): Call<List<Hero>>
}

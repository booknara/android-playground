package com.booknara.android.app.retrofit.network

import com.booknara.android.app.retrofit.model.Hero
import retrofit2.Response
import retrofit2.http.GET

interface HeroApi {
    @GET("marvel")
    suspend fun getHeroes(): Response<List<Hero>>
}

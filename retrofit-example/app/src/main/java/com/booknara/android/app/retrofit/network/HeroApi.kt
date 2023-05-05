package com.booknara.android.app.retrofit.network

import com.booknara.android.app.retrofit.model.Hero
import retrofit2.Call
import retrofit2.http.GET

interface HeroApi {
    @GET("marvel")
    fun getHeroes(): Call<List<Hero>>
    
//    @GET("marvel")
//    fun getHeroes(): Call<ResponseBody>    
}

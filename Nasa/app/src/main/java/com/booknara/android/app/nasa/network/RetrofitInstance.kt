package com.booknara.android.app.nasa.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"
const val API_KEY = "WTOcVnd3zwlCuUQUlbnVLb1ANjXpyPlU4W9hVUhw"
object RetrofitInstance {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
    
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    fun getNasaApi(): NasaApiService {
        return retrofit.create(NasaApiService::class.java)
    }
}

package com.booknara.android.app.retrofit.di

import android.content.Context
import com.booknara.android.app.retrofit.network.CustomHeaderInterceptor
import com.booknara.android.app.retrofit.network.HeroApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_SERVER_URL = "https://simplifiedcoding.net/demos/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context,
                            httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(CustomHeaderInterceptor())
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }
    
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideHeroApi(retrofit: Retrofit) : HeroApi {
        return retrofit.create(HeroApi::class.java)
    }
}

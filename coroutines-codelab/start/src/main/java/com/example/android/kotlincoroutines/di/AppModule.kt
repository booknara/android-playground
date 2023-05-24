package com.example.android.kotlincoroutines.di

import com.example.android.kotlincoroutines.main.MainNetwork
import com.example.android.kotlincoroutines.main.TitleDao
import com.example.android.kotlincoroutines.main.TitleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTitleRepository(network: MainNetwork, titleDao: TitleDao): TitleRepository {
        return TitleRepository(network, titleDao)
    }
}

package com.example.android.kotlincoroutines.di

import android.content.Context
import androidx.room.Room
import com.example.android.kotlincoroutines.main.TitleDao
import com.example.android.kotlincoroutines.main.TitleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TitleDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                TitleDatabase::class.java,
                "titles_db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    
    @Provides
    @Singleton
    fun provideTitleDao(titleDatabase: TitleDatabase) : TitleDao {
        return titleDatabase.titleDao
    }
}

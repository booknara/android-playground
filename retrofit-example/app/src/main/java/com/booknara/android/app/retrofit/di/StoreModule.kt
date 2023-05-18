package com.booknara.android.app.retrofit.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Module
@InstallIn(FragmentComponent::class)
abstract class StoreModule {
    @BookStoreQualifier
    @Binds
    abstract fun bookStoreImpl(bookStore: BookStore): Store

    @ClothingStoreQualifier
    @Binds
    abstract fun clothingStoreImpl(bookStore: ClothingStore): Store    
}

@Qualifier
annotation class BookStoreQualifier

@Qualifier
annotation class ClothingStoreQualifier

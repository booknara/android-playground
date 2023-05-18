package com.booknara.android.app.retrofit.di

import android.util.Log
import javax.inject.Inject

class ClothingStore @Inject constructor(): Store {
    override fun open() {
        Log.d(TAG, "open clothing store")
    }

    override fun close() {
        Log.d(TAG, "close clothing store")
    }
}

package com.booknara.android.app.retrofit.di

import android.util.Log
import javax.inject.Inject

class BookStore @Inject constructor(): Store {
    override fun open() {
        Log.d(TAG, "open bookstore")
    }

    override fun close() {
        Log.d(TAG, "close bookstore")
    }
}

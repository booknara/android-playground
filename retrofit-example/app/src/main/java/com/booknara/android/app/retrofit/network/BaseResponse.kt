package com.booknara.android.app.retrofit.network

sealed class BaseResponse<out T> {
    data class Success<out T>(val data: T? = null): BaseResponse<T>()
    data class Loading(val data: Nothing? = null): BaseResponse<Nothing>()
    data class Error(val msg: String?): BaseResponse<Nothing>()
}

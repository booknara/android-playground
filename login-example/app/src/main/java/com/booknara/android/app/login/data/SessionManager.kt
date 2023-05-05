package com.booknara.android.app.login.data

import android.content.Context
import android.content.SharedPreferences
import com.booknara.android.app.login.R

object SessionManager {
    const val USER_TOKEN = "user_token"
    
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, USER_TOKEN, token)
    }
    
    fun getToken(context: Context): String? {
        return getString(context, USER_TOKEN)
    }
    
    fun getString(context: Context, key: String): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    fun saveString(context: Context, key: String, value: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun clearData(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }    
}

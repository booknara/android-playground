package com.booknara.android.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("name") val name: String,
    @SerializedName("realname") val realname: String,
    val team: String,
    val firstappearance: String,
    val createdby: String,
    val publisher: String,
    val imageurl: String,
    val bio:String)

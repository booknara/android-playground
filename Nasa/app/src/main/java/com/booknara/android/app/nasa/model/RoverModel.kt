package com.booknara.android.app.nasa.model

import com.google.gson.annotations.SerializedName

data class RoverModel(
    val photos: List<Photo>
)

data class Photo(
    val camera: Camera,
    @SerializedName("earth_date")
    val earthDate: String,
    val id: Int,
    @SerializedName("img_src")
    val imgSrc: String,
    val rover: Rover,
    val sol: Int
)

data class Camera(
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    val name: String,
    @SerializedName("rover_id")
    val roverId: Int
)

data class Rover(
    val id: Int,
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launchDate: String,
    val name: String,
    val status: String
)

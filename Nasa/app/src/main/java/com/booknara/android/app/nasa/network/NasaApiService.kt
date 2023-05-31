package com.booknara.android.app.nasa.network

import com.booknara.android.app.nasa.model.Photo
import com.booknara.android.app.nasa.model.RoverModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Example queries
https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY

https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY

https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=DEMO_KEY
 */

interface NasaApiService {
    @GET("photos")
    suspend fun getCuriosity(@Query("sol") sol: Int, @Query("api_key") apiKey: String = API_KEY): Response<RoverModel>
}

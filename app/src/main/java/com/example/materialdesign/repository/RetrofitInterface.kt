package com.example.materialdesign.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface RetrofitInterface {
    @GET("planetary/apod")
    fun getPictureOfTheDay( @Query("api_key") apiKey : String): Call<ResponseData>

    @GET("planetary/apod")
    fun getPictureOfTheYesterday( @Query("date") dataTime : String, @Query("api_key") apiKey : String): Call<ResponseData>
}
package com.example.materialdesign.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey : String): Call<ResponseData>
}
package com.angelsit.kudagofeed.model

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("locations")
     fun listCity(): Call<List<City>>
}
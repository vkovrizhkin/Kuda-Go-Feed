package com.angelsit.kudagofeed.model

import retrofit2.http.GET
import retrofit2.http.Query

interface KudaGoApiServices {

    @GET("public-api/v1.4/events")
    fun getEvents(@Query("location") location: String) : Query
}
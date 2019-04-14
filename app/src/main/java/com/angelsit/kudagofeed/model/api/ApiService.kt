package com.angelsit.kudagofeed.model.api

import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.event.Event
import com.angelsit.kudagofeed.model.event.EventsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("locations")
    fun listCity(): Call<List<City>>

    @GET("events")
    fun getEvents(
        @Query("location") locationSlug: String,
        @Query("fields") fields: String = Event.fields,
        @Query("expand") expand: String = Event.expand,
        @Query("text_format") text_format: String = Event.textFormat

    ): Call<EventsResult>
}
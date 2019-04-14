package com.angelsit.kudagofeed.model.api

import com.angelsit.kudagofeed.model.City
import com.angelsit.kudagofeed.model.event.Event
import com.angelsit.kudagofeed.model.event.EventsResult
import com.angelsit.kudagofeed.model.eventdetails.EventDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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


    @GET("events/{id}")
    fun getEventDetails(
        @Path("id") id: String,
        @Query("expand") expand: String = EventDetails.expand,
        @Query("text_format") text_format: String = EventDetails.textFormat

    ): Call<EventDetails>

}
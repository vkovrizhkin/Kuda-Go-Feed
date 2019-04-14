package com.angelsit.kudagofeed.model

import com.angelsit.kudagofeed.MainContract
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


object Api {

    private val apiManager = ApiManager()

    fun getCities(listener: MainContract.Presenter.GetCitiesListener) {
        val call = apiManager.getService().listCity()
        call.enqueue(object : Callback<List<City>> {
            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                val cities = response.body()
                listener.onGetCityFinish(cities!!)
            }

        })

    }

    fun getEvents(citySlug: String, listener: MainContract.Presenter.GetEventsListener) {
        val call = apiManager.getService().getEvents("msk")
        call.enqueue(object : Callback<EventsResult> {
            override fun onFailure(call: Call<EventsResult>, t: Throwable) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<EventsResult>, response: Response<EventsResult>) {
                val events = response.body()
                val i = 0
               // listener.onGetEventsFinish(events!!)
            }

        })

    }

    fun getEventDetails(eventId: String, listener: MainContract.Presenter.GetEventDetailsListener) {
        val call = apiManager.getService().listCity()
        call.enqueue(object : Callback<List<City>> {
            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                val cities = response.body()
            }

        })

    }
}
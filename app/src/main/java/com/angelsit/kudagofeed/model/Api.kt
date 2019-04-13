package com.angelsit.kudagofeed.model

import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


object Api {

    private val apiManager = ApiManager()

    fun getCities(){
        val call = apiManager.getService().listCity()
        call.enqueue(object: Callback<List<City>>{
            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                val cities = response.body()
            }

        })

    }
}